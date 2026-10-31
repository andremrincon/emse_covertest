#!/usr/bin/env python3
"""04_validate_paper.py -- re-derive every number quoted in the revised manuscript
from the raw CSVs and check that it appears in the compiled PDF.
Usage: python3 04_validate_paper.py path/to/2025-emse-api-incremental.pdf"""
import sys, os, re, json, subprocess, warnings
import numpy as np, pandas as pd
warnings.filterwarnings("ignore")
sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
from common import load, MULTI, SINGLE
HERE = os.path.dirname(os.path.abspath(__file__))
N = json.load(open(os.path.join(HERE, "..", "results", "numbers.json")))
T = lambda f: pd.read_csv(os.path.join(HERE, "..", "tables", f))
pdf = sys.argv[1]
txt = re.sub(r"[\s\u00a0]+", " ", subprocess.run(["pdftotext", "-layout", pdf, "-"],
                                                  capture_output=True, text=True).stdout)
txt = txt.replace("−", "-")
e, s = load()
chk = []
def c(name, val, fmt="{:.2f}"):
    v = fmt.format(val); chk.append((name, v, v in txt))

# RQ1 (unchanged numbers still valid)
for k in ["T0", "C1", "C2", "C3", "gain", "rel_gain", "d_T0C3", "C1C3", "C1C3_dz"]: c(k, N[k])
# RQ2 new
c("friedman chi2", N["friedman"][0]); c("holm_min_signflip", N["holm_min_signflip"])
c("per-proj friedman min holm", N["per_project_friedman_min_holm"])
c("seq5 gap lo", N["seq5_gap_range"][0]); c("seq5 gap hi", N["seq5_gap_range"][1])
c("max margin w/o Seq5", N["max_margin_without_seq5"]); c("spread noFS", N["spread_noFS"])
chk.append(("equiv 1pp = 2/15", "2/15", "2/15" in txt and N["equiv_1"] == 2))
chk.append(("equiv 2pp = 10/15", "10/15", "10/15" in txt and N["equiv_2"] == 10))
chk.append(("equiv 2pp bonf = 6/15", "6/15", "6/15" in txt and N["equiv_2_bonf"] == 6))
chk.append(("FS other stalls 0/54", "54", N["fs_other_stall"] == [0, 54]))
chk.append(("FS fisher p<0.001", "", N["fs_fisher_p"] < 0.001))
# RQ3 totals
for k, fmt in [("cost", "{:.2f}"), ("hours", "{:.1f}")]:
    c("single " + k, N["camp_single"][k], fmt); c("all " + k, N["camp_all"][k], fmt)
chk.append(("all requests", "16,358", f"{N['camp_all']['requests']:,}" in txt))
# RQ4
r = T("rq4_matched_first_model.csv").set_index("first_model")
c("pooled dC3", r.loc["pooled", "dC3"]); c("pooled lo", r.loc["pooled", "ci_lo"]); c("pooled hi", r.loc["pooled", "ci_hi"])
c("pooled dz", r.loc["pooled", "dz"]); c("pooled dC1", r.loc["pooled", "dC1"])
c("pooled dC1 lo", r.loc["pooled", "dC1_lo"]); c("pooled dC1 hi", r.loc["pooled", "dC1_hi"])
c("gpt dC3", r.loc["gpt5mini", "dC3"]); c("gpt lo", r.loc["gpt5mini", "ci_lo"]); c("gpt hi", r.loc["gpt5mini", "ci_hi"])
c("glm dC3", r.loc["glm", "dC3"]); c("qwen dC3", r.loc["qwen", "dC3"])
c("wilcoxon pooled", r.loc["pooled", "p_wilcoxon"], "{:.3f}")
chk.append(("pooled signflip p<=0.005", "0.005", r.loc["pooled", "p_signflip"] <= 0.0055))
chk.append(("gpt holm <0.001", "", r.loc["gpt5mini", "p_signflip_holm"] < 0.001))
rs = N["rq4_rescue"]
chk.append(("rescue multi 31/39", "31 of the 39", rs["multi"] == [31, 39] and "31 of the 39" in txt))
chk.append(("rescue single 7/19", "7 of 19", rs["single"] == [7, 19] and "7 of 19" in txt))
c("rescue OR", rs["odds_ratio"], "{:.1f}"); c("rescue p", rs["p"], "{:.3f}")
chk.append(("cells 10+/5-", "10 cells", N["rq4_cells"]["positive"] == 10 and N["rq4_cells"]["negative"] == 5))
c("cells p", N["rq4_cells"]["p"])
bp = T("rq4_by_project.csv").set_index("project")
for pr in ["spring-actuator-demo", "features-service"]:
    for col in ["dC3", "ci_lo", "ci_hi"]: c(f"{pr} {col}", bp.loc[pr, col])
chk.append(("other systems |d|<0.4", "", bp.drop(["spring-actuator-demo", "features-service"]).dC3.abs().max() < 0.4))
bs = T("rq4_best_single_vs_multi.csv")
c("best single min", bs.diff_vs_qwen3.min()); c("best single max", bs.diff_vs_qwen3.max())
chk.append(("best single holm>=0.90", "0.90", bs.p_holm.min() >= 0.90))
c("single friedman chi2", N["rq4_single_friedman"][0])
sp = s[s.arm == "single"].groupby("step_position").coverage_gain.mean()
c("single pos2", sp[2]); c("single pos3", sp[3])
g = e[e.arm == "single"].groupby("sequence").C3.mean(); c("single spread", g.max() - g.min())
c("sens rq4", N["sens_rq4_pooled"][0]); c("sens rq4 hi", N["sens_rq4_pooled"][2])
seq = T("rq3_by_sequence.csv").set_index("tag")
for t in ["Seq7", "Seq8", "Seq9"]:
    c(t + " gain/$", seq.loc[t, "gain_per_usd"], "{:.1f}"); c(t + " cost", seq.loc[t, "cost"], "{:.3f}")
c("glm x3 / multi cost", seq.loc["Seq7", "cost"] / e[e.arm == "multi"].total_cost_usd.mean(), "{:.1f}")
chk.append(("noop 91/60/31", "91", (N["noop_steps_total"], N["noop_steps_multi"], N["noop_steps_single"]) == (91, 60, 31)))

bad = [x for x in chk if not x[2]]
print(f"CHECKS {len(chk)}  PASS {len(chk)-len(bad)}  FAIL {len(bad)}")
for b in bad: print("  FAIL", b)

# ---- pipeline-failure sensitivity (added in round 3)
chk2 = []
def c2(name, val, fmt="{:.2f}"):
    v = fmt.format(val); chk2.append((name, v, v in txt))
c2("pf Seq5", N["pf_seq5_C3"]); c2("pf spread", N["pf_spread"])
chk2.append(("pf equiv 14 of 15", "14 of the 15", N["pf_equiv_2"] == 14 and "14 of the 15" in txt))
c2("pf rq4 pooled", N["pf_rq4_pooled"][0]); c2("pf lo", N["pf_rq4_pooled"][1]); c2("pf hi", N["pf_rq4_pooled"][2])
chk2.append(("pf p=0.002", "0.002", round(N["pf_rq4_pooled"][3], 3) <= 0.002))
c2("pf glm", N["pf_rq4_glm"][0])
x = e[e.stall1 & ~e.pipeline_failure].assign(rec=lambda d: d.gain_C1C3 >= 1)
r2 = x.groupby("arm").rec.agg(["sum", "count"])
chk2.append(("rescue excl 31/36, 7/16", "31 of 36", (int(r2.loc["multi","sum"]), int(r2.loc["multi","count"]),
             int(r2.loc["single","sum"]), int(r2.loc["single","count"])) == (31, 36, 7, 16) and "31 of 36" in txt))
bad2 = [x for x in chk2 if not x[2]]
print(f"PIPELINE-FAILURE CHECKS {len(chk2)}  PASS {len(chk2)-len(bad2)}  FAIL {len(bad2)}")
for b in bad2: print("  FAIL", b)
