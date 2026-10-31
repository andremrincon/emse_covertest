#!/usr/bin/env python3
"""
02_analysis.py -- statistical analysis for the EMSE paper (405 executions).

Arms
  multi  : Seq1-Seq6, the six permutations of {qwen, gpt5mini, glm}  (270 runs)
  single : Seq7 glm x3, Seq8 qwen x3, Seq9 gpt5mini x3 (control)      (135 runs)

RQ1  coverage gain of the multi-model pipeline and its stage decomposition
RQ2  effect of model ordering (omnibus + Holm pairwise + TOST equivalence,
     per-system interaction)
RQ3  marginal cost profile (ratio-of-means efficiencies) + campaign totals
RQ4  single-model control: does model diversity add value beyond repeating
     the same model?  Contrasts are matched on the FIRST model, because
     position 1 receives the same treatment in both arms; any difference in
     C3 is therefore attributable to positions 2-3 (repeat vs switch).

Unit of analysis: block = (project, seed, run), n = 45 per configuration.
Executions are NOT mutually independent; tests pair configurations within
blocks, and cell-level (project x configuration) robustness is reported.

Outputs: ../tables/*.csv  and  ../results/numbers.json
"""
import os, json, sys, warnings
from itertools import combinations
import numpy as np
import pandas as pd
from scipy import stats

warnings.filterwarnings("ignore")
sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
from common import (load, BLOCK, MULTI, SINGLE, SEQ_TAG, MODELS, STALL_PP)

HERE = os.path.dirname(os.path.abspath(__file__))
TAB = os.path.join(HERE, "..", "tables"); os.makedirs(TAB, exist_ok=True)
RES = os.path.join(HERE, "..", "results"); os.makedirs(RES, exist_ok=True)
RNG = np.random.default_rng(20260920)
NB, NP = 10_000, 50_000               # bootstrap / permutation resamples
DELTA = (1.0, 2.0)                    # equivalence margins (pp) for RQ2
N = {}                                # every number quoted in the paper


def save(df, name):
    df.to_csv(os.path.join(TAB, name), index=False)


def boot_ci(d, conf=0.95):
    d = np.asarray(d, float)
    m = RNG.choice(d, (NB, len(d))).mean(axis=1)
    a = (1 - conf) / 2
    return float(np.percentile(m, 100 * a)), float(np.percentile(m, 100 * (1 - a)))


def signflip_p(d):
    """Exact-style paired permutation test on the MEAN difference."""
    d = np.asarray(d, float)
    if np.allclose(d, 0):
        return 1.0
    sg = RNG.choice([-1.0, 1.0], (NP, len(d)))
    return float((np.abs((sg * d).mean(axis=1)) >= abs(d.mean()) - 1e-12).mean())


def wilcox_p(d):
    d = np.asarray(d, float)
    return 1.0 if np.allclose(d, 0) else float(stats.wilcoxon(d)[1])


def dz(d):
    d = np.asarray(d, float); sd = d.std(ddof=1)
    return float(d.mean() / sd) if sd > 0 else float("nan")


def holm(p):
    p = np.asarray(p, float); o = np.argsort(p); m = len(p)
    out = np.empty(m); prev = 0.0
    for i, j in enumerate(o):
        prev = max(prev, min((m - i) * p[j], 1.0)); out[j] = prev
    return out


def wide(e, value="C3", seqs=None):
    w = e.pivot_table(index=BLOCK, columns="sequence", values=value)
    return w[seqs] if seqs else w


# ---------------------------------------------------------------- data checks
def data_checks(e, s):
    N["n_exec"] = int(len(e)); N["n_steps"] = int(len(s))
    N["n_multi"] = int((e.arm == "multi").sum()); N["n_single"] = int((e.arm == "single").sum())
    per = e.groupby("sequence").size()
    assert (per == 45).all(), "unbalanced design"
    assert e.groupby("project").T0.nunique().eq(1).all(), "T0 not constant"
    N["noop_steps_total"] = int((s.cost_usd == 0).sum())
    N["noop_steps_multi"] = int(((s.cost_usd == 0) & (s.arm == "multi")).sum())
    N["noop_steps_single"] = int(((s.cost_usd == 0) & (s.arm == "single")).sum())
    assert ((s.cost_usd == 0) & (s.coverage_gain > 0)).sum() == 0
    assert (s[s.cost_usd == 0].project == "spring-actuator-demo").all()
    save(e.groupby(["sequence", "sequence_label"]).size().reset_index(name="n"), "design_check.csv")


# ---------------------------------------------------------------------- RQ1
def rq1(e, s):
    m = e[e.arm == "multi"]; sm = s[s.arm == "multi"]
    N["T0"] = m.T0.mean(); N["C1"] = m.C1.mean(); N["C2"] = m.C2.mean(); N["C3"] = m.C3.mean()
    N["gain"] = (m.C3 - m.T0).mean(); N["rel_gain"] = N["gain"] / N["T0"] * 100
    N["d_T0C3"] = dz(m.C3 - m.T0)

    proj = m.groupby("project").agg(T0=("T0", "mean"), C3=("C3", "mean")).reset_index()
    proj["abs_gain"] = proj.C3 - proj.T0; proj["rel_gain"] = proj.abs_gain / proj.T0 * 100
    save(proj, "rq1_by_project.csv")

    rows, ps = [], []
    for nm, b, a in [("T0->C1", "T0", "C1"), ("C1->C2", "C1", "C2"), ("C2->C3", "C2", "C3"),
                     ("C1->C3", "C1", "C3"), ("T0->C3", "T0", "C3")]:
        d = m[a] - m[b]; lo, hi = boot_ci(d); p = wilcox_p(d); ps.append(p)
        rows.append(dict(comparison=nm, before=m[b].mean(), after=m[a].mean(), diff=d.mean(),
                         dz=dz(d), ci_lo=lo, ci_hi=hi, p=p))
    t = pd.DataFrame(rows); t["p_holm"] = holm(ps); save(t, "rq1_stagewise.csv")
    c = t.set_index("comparison")
    N["C1C3"] = c.loc["C1->C3", "diff"]; N["C1C3_dz"] = c.loc["C1->C3", "dz"]
    N["C1C3_ci"] = [c.loc["C1->C3", "ci_lo"], c.loc["C1->C3", "ci_hi"]]
    N["frac_gain_beyond_C1"] = float(((m.C3 - m.C1) > 1e-9).mean() * 100)

    pos = sm.groupby("step_position").coverage_gain.agg(["mean", "median", "std", "sum"]).reset_index()
    pos["share"] = pos["sum"] / pos["sum"].sum() * 100; save(pos, "rq1_by_position.csv")
    N["pos_means"] = pos["mean"].tolist(); N["pos_share"] = pos["share"].tolist()

    lead = [dict(first=f, delta=(g.C3 - g.C1).mean(), p=wilcox_p(g.C3 - g.C1))
            for f, g in m.groupby("model_1")]
    save(pd.DataFrame(lead), "rq1_increment_by_leading_model.csv")

    cell = m.groupby(["sequence", "project"]).agg(T0=("T0", "mean"), C1=("C1", "mean"),
                                                  C3=("C3", "mean")).reset_index()
    N["cell_T0C3"] = (cell.C3 - cell.T0).mean(); N["cell_T0C3_dz"] = dz(cell.C3 - cell.T0)
    N["cell_C1C3"] = (cell.C3 - cell.C1).mean(); N["cell_C1C3_dz"] = dz(cell.C3 - cell.C1)


# ---------------------------------------------------------------------- RQ2
def rq2(e):
    m = e[e.arm == "multi"]; w = wide(m, "C3", MULTI)
    fr = stats.friedmanchisquare(*[w[c] for c in MULTI])
    kw = stats.kruskal(*[g.C3.values for _, g in m.groupby("sequence")])
    N["friedman"] = [fr.statistic, fr.pvalue]; N["kruskal"] = [kw.statistic, kw.pvalue]

    pairs = list(combinations(MULTI, 2)); rows = []
    for a, b in pairs:
        d = w[a] - w[b]
        lo90, hi90 = boot_ci(d, 0.90)
        loB, hiB = boot_ci(d, 1 - 2 * 0.05 / len(pairs))   # Bonferroni-adjusted TOST
        rows.append(dict(a=SEQ_TAG[a], b=SEQ_TAG[b], diff=d.mean(),
                         p_wilcoxon=wilcox_p(d), p_signflip=signflip_p(d),
                         ci90_lo=lo90, ci90_hi=hi90, ciBonf_lo=loB, ciBonf_hi=hiB,
                         min_equiv_margin=max(abs(lo90), abs(hi90))))
    t = pd.DataFrame(rows)
    t["p_wilcoxon_holm"] = holm(t.p_wilcoxon); t["p_signflip_holm"] = holm(t.p_signflip)
    for dl in DELTA:
        t[f"equiv_{dl:g}pp"] = (t.ci90_lo > -dl) & (t.ci90_hi < dl)
        t[f"equiv_{dl:g}pp_bonf"] = (t.ciBonf_lo > -dl) & (t.ciBonf_hi < dl)
    save(t, "rq2_pairwise_equivalence.csv")
    N["holm_min_wilcoxon"] = float(t.p_wilcoxon_holm.min())
    N["holm_min_signflip"] = float(t.p_signflip_holm.min())
    for dl in DELTA:
        N[f"equiv_{dl:g}"] = int(t[f"equiv_{dl:g}pp"].sum())
        N[f"equiv_{dl:g}_bonf"] = int(t[f"equiv_{dl:g}pp_bonf"].sum())
    no5 = t[(t.a != "Seq5") & (t.b != "Seq5")]
    N["max_margin_without_seq5"] = float(no5.min_equiv_margin.max())
    s5 = t[(t.a == "Seq5") | (t.b == "Seq5")]
    N["seq5_gap_range"] = [float(s5["diff"].abs().min()), float(s5["diff"].abs().max())]

    # system x ordering interaction
    pr_rows = []
    for pr in sorted(m.project.unique()):
        wp = m[m.project == pr].pivot_table(index=["seed", "run"], columns="sequence", values="C3")[MULTI]
        f = stats.friedmanchisquare(*[wp[c] for c in MULTI])
        pr_rows.append(dict(project=pr, chi2=f.statistic, p=f.pvalue))
    ptab = pd.DataFrame(pr_rows); ptab["p_holm"] = holm(ptab.p); save(ptab, "rq2_friedman_by_project.csv")
    N["per_project_friedman_min_holm"] = float(ptab.p_holm.min())

    # features-service: first-stage stalls by leading model, all 9 configs
    fs = e[e.project == "features-service"]
    st = fs.groupby(["tag", "model_1"]).agg(n=("stall1", "size"), stall1=("stall1", "sum"),
                                            total_stall=("C3", lambda x: 0)).reset_index()
    st["total_stall"] = fs.assign(ts=(fs.C3 - fs.T0) < STALL_PP).groupby("tag").ts.sum().values
    save(st, "rq2_features_service_stalls.csv")
    glm_led = fs[fs.model_1 == "glm"]; other = fs[fs.model_1 != "glm"]
    N["fs_glm_stall"] = [int(glm_led.stall1.sum()), int(len(glm_led))]
    N["fs_other_stall"] = [int(other.stall1.sum()), int(len(other))]
    N["fs_fisher_p"] = float(stats.fisher_exact(
        [[glm_led.stall1.sum(), len(glm_led) - glm_led.stall1.sum()],
         [other.stall1.sum(), len(other) - other.stall1.sum()]])[1])
    N["fs_seq5_total_stalls"] = int(((fs.tag == "Seq5") & ((fs.C3 - fs.T0) < STALL_PP)).sum())

    # sensitivity: ordering spread without features-service
    m2 = m[m.project != "features-service"]; w2 = wide(m2, "C3", MULTI)
    f2 = stats.friedmanchisquare(*[w2[c] for c in MULTI]); N["friedman_noFS"] = [f2.statistic, f2.pvalue]
    g = m2.groupby("sequence").C3.mean(); N["spread_noFS"] = float(g.max() - g.min())
    g1 = m.groupby("sequence").C3.mean(); N["spread_all"] = float(g1.max() - g1.min())


# ---------------------------------------------------------------------- RQ3
def eff(df_gain, df_cost, df_tok):
    return df_gain / df_cost, df_gain / (df_tok / 1000)


def rq3(e, s):
    rows = []
    for (sq, lbl), g in e.groupby(["sequence", "sequence_label"]):
        gpu, gpt = eff(g.coverage_gain.mean(), g.total_cost_usd.mean(), g.total_tokens.mean())
        rows.append(dict(sequence=sq, tag=SEQ_TAG[sq], label=lbl, arm=g.arm.iloc[0],
                         cost=g.total_cost_usd.mean(), tokens=g.total_tokens.mean(),
                         gain=g.coverage_gain.mean(), minutes=g.total_execution_time_minutes.mean(),
                         gain_per_usd=gpu, gain_per_1k_tok=gpt,
                         cost_total=g.total_cost_usd.sum(), tokens_total=g.total_tokens.sum(),
                         requests_total=g.total_requests.sum(),
                         hours_total=g.total_execution_time_minutes.sum() / 60))
    t = pd.DataFrame(rows); save(t, "rq3_by_sequence.csv")
    for arm, g in e.groupby("arm"):
        N[f"camp_{arm}"] = dict(cost=g.total_cost_usd.sum(), tokens=int(g.total_tokens.sum()),
                               requests=int(g.total_requests.sum()),
                               hours=g.total_execution_time_minutes.sum() / 60,
                               cost_per_run=g.total_cost_usd.mean())
    N["camp_all"] = dict(cost=e.total_cost_usd.sum(), tokens=int(e.total_tokens.sum()),
                         requests=int(e.total_requests.sum()),
                         hours=e.total_execution_time_minutes.sum() / 60)

    per = []
    for (arm, mdl), g in s.groupby(["arm", "model"]):
        gpu, gpt = eff(g.coverage_gain.mean(), g.cost_usd.mean(), g.total_tokens.mean())
        per.append(dict(arm=arm, model=mdl, gain=g.coverage_gain.mean(), cost=g.cost_usd.mean(),
                        tokens=g.total_tokens.mean(), gain_per_usd=gpu, gain_per_1k_tok=gpt))
    save(pd.DataFrame(per), "rq3_by_model_and_arm.csv")

    mp = []
    for (arm, mdl, pos), g in s.groupby(["arm", "model", "step_position"]):
        mp.append(dict(arm=arm, model=mdl, position=pos, gain=g.coverage_gain.mean(),
                       cost=g.cost_usd.mean(), gain_per_usd=g.coverage_gain.mean() / g.cost_usd.mean()))
    save(pd.DataFrame(mp), "rq3_by_model_position_and_arm.csv")


# ---------------------------------------------------------------------- RQ4
FIRST = {"glm": (["sequence_05", "sequence_06"], "sequence_07"),
         "qwen": (["sequence_01", "sequence_02"], "sequence_08"),
         "gpt5mini": (["sequence_03", "sequence_04"], "sequence_09")}


def matched(e, value):
    w = wide(e, value); out = {}
    for f, (mu, si) in FIRST.items():
        out[f] = w[mu].mean(axis=1) - w[si]
    out["pooled"] = pd.concat([out[f] for f in FIRST], axis=1).mean(axis=1)
    return out


def rq4(e, s, tag=""):
    res = {}
    rows = []
    D3 = matched(e, "C3"); D1 = matched(e, "C1"); Dinc = matched(e, "gain_C1C3")
    pvals = []
    for f in list(FIRST) + ["pooled"]:
        mu, si = FIRST.get(f, (MULTI, SINGLE))
        gm = e[e.sequence.isin(mu)]; gs = e[e.sequence.isin(si if isinstance(si, list) else [si])]
        d = D3[f]; lo, hi = boot_ci(d); psf = signflip_p(d); pw = wilcox_p(d)
        l1, h1 = boot_ci(D1[f])
        rows.append(dict(first_model=f,
                         multi_C1=gm.C1.mean(), single_C1=gs.C1.mean(), dC1=D1[f].mean(),
                         dC1_lo=l1, dC1_hi=h1, p_C1=signflip_p(D1[f]),
                         multi_inc=gm.gain_C1C3.mean(), single_inc=gs.gain_C1C3.mean(),
                         multi_C3=gm.C3.mean(), single_C3=gs.C3.mean(),
                         dC3=d.mean(), ci_lo=lo, ci_hi=hi, dz=dz(d),
                         p_signflip=psf, p_wilcoxon=pw,
                         multi_cost=gm.total_cost_usd.mean(), single_cost=gs.total_cost_usd.mean()))
        if f != "pooled":
            pvals.append(psf)
    t = pd.DataFrame(rows)
    t["p_signflip_holm"] = list(holm(pvals)) + [np.nan]
    res["table"] = t

    # stall rescue: first stage adds < 1 pp -> does positions 2-3 recover >= 1 pp?
    st = e[e.stall1].assign(rec=lambda x: x.gain_C1C3 >= STALL_PP)
    r = st.groupby("arm").rec.agg(["sum", "count"])
    fe = stats.fisher_exact([[r.loc["multi", "sum"], r.loc["multi", "count"] - r.loc["multi", "sum"]],
                             [r.loc["single", "sum"], r.loc["single", "count"] - r.loc["single", "sum"]]])
    res["rescue"] = dict(multi=[int(r.loc["multi", "sum"]), int(r.loc["multi", "count"])],
                         single=[int(r.loc["single", "sum"]), int(r.loc["single", "count"])],
                         odds_ratio=float(fe[0]), p=float(fe[1]))
    res["rescue_detail"] = (st.groupby(["arm", "project", "model_1"]).rec
                              .agg(["sum", "count"]).reset_index())

    # per-project decomposition of the pooled diversity effect
    pp = []
    for pr in sorted(e.project.unique()):
        d = matched(e[e.project == pr], "C3")["pooled"]; lo, hi = boot_ci(d)
        pp.append(dict(project=pr, dC3=d.mean(), ci_lo=lo, ci_hi=hi))
    res["by_project"] = pd.DataFrame(pp)

    # cell-level robustness: 5 projects x 3 first models = 15 independent cells
    cells = []
    for pr in sorted(e.project.unique()):
        ep = e[e.project == pr]
        for f, (mu, si) in FIRST.items():
            cells.append(ep[ep.sequence.isin(mu)].C3.mean() - ep[ep.sequence == si].C3.mean())
    cells = np.array(cells)
    res["cells"] = dict(mean=float(cells.mean()), p=signflip_p(cells), n=len(cells),
                        positive=int((cells > 0.05).sum()), negative=int((cells < -0.05).sum()))

    # best single vs every multi ordering
    w = wide(e, "C3"); cmp = []
    for sq in MULTI:
        d = w[sq] - w["sequence_08"]; lo, hi = boot_ci(d)
        cmp.append(dict(multi=SEQ_TAG[sq], diff_vs_qwen3=d.mean(), ci_lo=lo, ci_hi=hi, p=signflip_p(d)))
    c = pd.DataFrame(cmp); c["p_holm"] = holm(c.p); res["best_single"] = c

    # arm-level spread
    res["arm_C3"] = e.groupby("arm").C3.mean().to_dict()
    res["arm_cost"] = e.groupby("arm").total_cost_usd.mean().to_dict()
    res["single_friedman"] = list(stats.friedmanchisquare(*[w[c] for c in SINGLE]))
    return res


def main():
    e, s = load()
    data_checks(e, s)
    rq1(e, s); rq2(e); rq3(e, s)
    r = rq4(e, s)
    r["table"].to_csv(os.path.join(TAB, "rq4_matched_first_model.csv"), index=False)
    r["rescue_detail"].to_csv(os.path.join(TAB, "rq4_stall_rescue_detail.csv"), index=False)
    r["by_project"].to_csv(os.path.join(TAB, "rq4_by_project.csv"), index=False)
    r["best_single"].to_csv(os.path.join(TAB, "rq4_best_single_vs_multi.csv"), index=False)
    N["rq4_rescue"] = r["rescue"]; N["rq4_cells"] = r["cells"]
    N["rq4_arm_C3"] = r["arm_C3"]; N["rq4_arm_cost"] = r["arm_cost"]
    N["rq4_single_friedman"] = r["single_friedman"]

    # sensitivity: exclude the two repaired records
    ex, sx = load(exclude_repaired=True)
    m = ex[ex.arm == "multi"]
    N["sens_gain"] = (m.C3 - m.T0).mean(); N["sens_d"] = dz(m.C3 - m.T0)
    N["sens_C1C3"] = (m.C3 - m.C1).mean(); N["sens_C1C3_dz"] = dz(m.C3 - m.C1)
    N["sens_pos"] = sx[sx.arm == "multi"].groupby("step_position").coverage_gain.mean().tolist()
    w = m.pivot_table(index=BLOCK, columns="sequence", values="C3")[MULTI].dropna()
    N["sens_friedman_p"] = float(stats.friedmanchisquare(*[w[c] for c in MULTI]).pvalue)
    wall = ex.pivot_table(index=BLOCK, columns="sequence", values="C3").dropna()
    d = pd.concat([wall[mu].mean(axis=1) - wall[si] for mu, si in FIRST.values()], axis=1).mean(axis=1)
    N["sens_rq4_pooled"] = [float(d.mean()), *boot_ci(d)]

    # sensitivity: exclude the six features-service pipeline failures
    ok = e[~e.pipeline_failure]
    assert e.pipeline_failure.sum() == 6
    g = ok[ok.arm == "multi"].groupby("sequence").C3.mean()
    N["pf_seq5_C3"] = float(g["sequence_05"]); N["pf_spread"] = float(g.max() - g.min())
    wm = ok.pivot_table(index=BLOCK, columns="sequence", values="C3")
    margins = []
    for a, b in combinations(MULTI, 2):
        dd = (wm[a] - wm[b]).dropna(); lo, hi = boot_ci(dd, 0.90); margins.append(max(abs(lo), abs(hi)))
    margins = np.array(margins)
    N["pf_equiv_1"] = int((margins < 1).sum()); N["pf_equiv_2"] = int((margins < 2).sum())
    N["pf_max_margin"] = float(margins.max())
    N["pf_friedman_p"] = float(stats.friedmanchisquare(*[wm[c].dropna() for c in MULTI]).pvalue) \
        if wm[MULTI].dropna().shape[0] == wm[MULTI].shape[0] else float(
        stats.friedmanchisquare(*[wm[MULTI].dropna()[c] for c in MULTI]).pvalue)
    ds = {}
    for f, (mu, si) in FIRST.items():
        ds[f] = (wm[mu].mean(axis=1) - wm[si]).dropna()
        lo, hi = boot_ci(ds[f]); N[f"pf_rq4_{f}"] = [float(ds[f].mean()), lo, hi, signflip_p(ds[f])]
    pooled = pd.concat(ds, axis=1).mean(axis=1).dropna()
    lo, hi = boot_ci(pooled); N["pf_rq4_pooled"] = [float(pooled.mean()), lo, hi, signflip_p(pooled)]

    def clean(o):
        if isinstance(o, dict): return {k: clean(v) for k, v in o.items()}
        if isinstance(o, (list, tuple)): return [clean(v) for v in o]
        if isinstance(o, (np.floating, np.integer)): return o.item()
        return o
    with open(os.path.join(RES, "numbers.json"), "w") as f:
        json.dump(clean(N), f, indent=1)
    print(r["table"].round(3).to_string(index=False))
    print(json.dumps(clean({k: N[k] for k in ["rq4_rescue", "rq4_cells", "equiv_1", "equiv_2",
                                                "equiv_2_bonf", "max_margin_without_seq5",
                                                "holm_min_wilcoxon", "holm_min_signflip",
                                                "fs_glm_stall", "fs_other_stall", "fs_fisher_p",
                                                "sens_rq4_pooled", "friedman_noFS", "spread_noFS",
                                                "pf_seq5_C3", "pf_spread", "pf_equiv_1", "pf_equiv_2",
                                                "pf_max_margin", "pf_friedman_p", "pf_rq4_glm",
                                                "pf_rq4_pooled"]}), indent=1))
    print(r["best_single"].round(3).to_string(index=False))
    print(r["by_project"].round(2).to_string(index=False))


if __name__ == "__main__":
    main()
