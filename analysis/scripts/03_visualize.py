#!/usr/bin/env python3
"""
03_visualize.py -- figures for the EMSE paper (405 executions).

All nine configurations together (multi-model Seq1-6 solid, single-model
control Seq7-9 dashed/hatched):
  fig03_coverage_evolution   fig04_initial_vs_final   fig06_final_cov_by_project
  fig07_sequence_boxplot     fig09_cost_benefit_ranking
Split into multi-model (a) and single-model (b), same axis scales:
  fig05a/b_gain_by_position  fig08a/b_cost_vs_coverage  fig10a/b_efficiency_by_model

File names match the \\includegraphics names used in the manuscript.
Efficiencies are ratios of means (mean gain / mean cost).
"""
import os, sys
import numpy as np
import pandas as pd
import matplotlib
matplotlib.use("Agg")
import matplotlib.pyplot as plt

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
from common import load, MULTI, SINGLE, SEQ_ORDER, SEQ_TAG

HERE = os.path.dirname(os.path.abspath(__file__))
FIG = os.path.join(HERE, "..", "figures"); os.makedirs(FIG, exist_ok=True)

plt.style.use("seaborn-v0_8-whitegrid")
plt.rcParams.update({"figure.dpi": 150, "savefig.dpi": 300, "font.size": 10,
                     "axes.titlesize": 11, "axes.titleweight": "bold",
                     "legend.fontsize": 8, "font.family": "DejaVu Sans"})
COL = {"sequence_01": "#20808D", "sequence_02": "#A84B2F", "sequence_03": "#1B474D",
       "sequence_04": "#E0A526", "sequence_05": "#944454", "sequence_06": "#6E7B2F",
       "sequence_07": "#7B3294", "sequence_08": "#1F78B4", "sequence_09": "#D95F02"}
MCOL = {"qwen": "#20808D", "gpt5mini": "#A84B2F", "glm": "#E0A526"}


def lab(e):
    m = e.drop_duplicates("sequence").set_index("sequence").sequence_label.to_dict()
    return {s: f"{SEQ_TAG[s]}: {m[s].replace(' -> ', '→')}" for s in SEQ_ORDER}


def save(fig, name):
    for ext in ("pdf", "png"):
        fig.savefig(os.path.join(FIG, f"{name}.{ext}"), bbox_inches="tight")
    plt.close(fig); print("  wrote", name)


def fig03(e):
    L = lab(e); fig, ax = plt.subplots(figsize=(8.2, 5))
    for sq in SEQ_ORDER:
        g = e[e.sequence == sq]; y = [g.T0.mean(), g.C1.mean(), g.C2.mean(), g.C3.mean()]
        ls = "-" if sq in MULTI else "--"
        ax.plot(range(4), y, ls, marker="o" if sq in MULTI else "s", color=COL[sq],
                lw=1.5, ms=4.5, label=L[sq])
    ax.set_xticks(range(4)); ax.set_xticklabels(["T0 (initial)", "after model 1", "after model 2", "after model 3"])
    ax.set_ylabel("Mean instruction coverage (%)")
    ax.set_title("Coverage evolution per configuration (solid: multi-model; dashed: single-model)", loc="left")
    ax.legend(fontsize=7, loc="lower right", ncol=1)
    save(fig, "fig03_coverage_evolution")


def fig04(e):
    L = lab(e); g = e.groupby("sequence").agg(T0=("T0", "mean"), C3=("C3", "mean"), sd=("C3", "std")).reindex(SEQ_ORDER)
    x = np.arange(len(SEQ_ORDER)); w = 0.38
    fig, ax = plt.subplots(figsize=(10, 5))
    ax.bar(x - w / 2, g.T0, w, color="#C9DDE0", label="Initial (T0)")
    b = ax.bar(x + w / 2, g.C3, w, yerr=g.sd, capsize=3, color=[COL[s] for s in SEQ_ORDER],
               hatch=[None] * 6 + ["//"] * 3, edgecolor="white", label="Final (after 3 stages)")
    ax.bar_label(b, fmt="%.1f", fontsize=7, padding=2)
    ax.axvline(5.5, color="grey", lw=0.8, ls=":")
    ax.text(2.5, 104, "multi-model", ha="center", fontsize=8, color="grey")
    ax.text(7, 104, "single-model control", ha="center", fontsize=8, color="grey")
    ax.set_xticks(x); ax.set_xticklabels([L[s] for s in SEQ_ORDER], rotation=25, ha="right", fontsize=7)
    ax.set_ylabel("Instruction coverage (%)"); ax.set_ylim(0, 110)
    ax.legend(loc="lower right", fontsize=8)
    save(fig, "fig04_initial_vs_final")


def fig05(s, e):
    for arm, suffix, title in [("multi", "a", "Multi-model configurations (Seq1-6)"),
                               ("single", "b", "Single-model control (Seq7-9)")]:
        g = s[s.arm == arm]; fig, ax = plt.subplots(figsize=(7, 4.4))
        pooled = g.groupby("step_position").coverage_gain.agg(["mean", "std"])
        ax.bar([1, 2, 3], pooled["mean"], yerr=pooled["std"], capsize=5, width=0.62,
               color=["#20808D", "#5FA3AC", "#9FC6CC"], alpha=0.35, label="pooled mean (± SD)")
        off = {"qwen": -0.18, "gpt5mini": 0.0, "glm": 0.18}
        for mdl, o in off.items():
            mm = g[g.model == mdl].groupby("step_position").coverage_gain.mean()
            ax.scatter(mm.index + o, mm.values, color=MCOL[mdl], s=36, zorder=3, label=mdl)
        ax.set_xticks([1, 2, 3])
        ax.set_xticklabels([f"Position {p}\n(pooled {v:.2f} pp)" for p, v in zip([1, 2, 3], pooled["mean"])])
        ax.set_ylabel("Mean marginal coverage gain (pp)"); ax.set_ylim(-4, 27)
        ax.set_title(title, loc="left"); ax.legend(fontsize=8, loc="upper right")
        save(fig, f"fig05{suffix}_gain_by_position_{arm}")


def fig06(e):
    L = lab(e)
    pv = e.pivot_table(index="sequence", columns="project", values="C3").reindex(SEQ_ORDER)
    fig, ax = plt.subplots(figsize=(8.4, 5.6))
    im = ax.imshow(pv.values, cmap="YlGnBu", aspect="auto", vmin=70, vmax=100)
    ax.grid(False)
    ax.set_xticks(range(pv.shape[1])); ax.set_xticklabels(pv.columns, rotation=20, ha="right", fontsize=8)
    ax.set_yticks(range(pv.shape[0])); ax.set_yticklabels([L[s] for s in pv.index], fontsize=7)
    for i in range(pv.shape[0]):
        for j in range(pv.shape[1]):
            v = pv.values[i, j]
            ax.text(j, i, f"{v:.1f}", ha="center", va="center", fontsize=7.5,
                    color="white" if v > 90 else "black")
    ax.axhline(5.5, color="black", lw=1.2)
    fig.colorbar(im, ax=ax, label="Final instruction coverage (%)")
    ax.set_title("Mean final coverage per configuration and system (9 replicates per cell)", loc="left", fontsize=10)
    save(fig, "fig06_final_cov_by_project")


def fig07(e):
    L = lab(e); data = [e[e.sequence == s].C3.values for s in SEQ_ORDER]
    fig, ax = plt.subplots(figsize=(10, 5))
    bp = ax.boxplot(data, patch_artist=True, showmeans=True,
                    meanprops=dict(marker="D", markerfacecolor="white", markeredgecolor="black", markersize=5))
    for i, (patch, s) in enumerate(zip(bp["boxes"], SEQ_ORDER)):
        patch.set_facecolor(COL[s]); patch.set_alpha(0.75)
        if s in SINGLE: patch.set_hatch("//")
    ax.axvline(6.5, color="grey", lw=0.8, ls=":")
    ax.set_xticklabels([L[s] for s in SEQ_ORDER], rotation=25, ha="right", fontsize=7)
    ax.set_ylabel("Final instruction coverage (%)")
    ax.set_title("Final-coverage distribution per configuration (n = 45 each; diamonds = means)", loc="left")
    save(fig, "fig07_sequence_boxplot")


def fig08(e):
    xmax = e.total_cost_usd.max() * 1.05; ymax = e.coverage_gain.max() + 2
    for arm, suffix, seqs, title in [("multi", "a", MULTI, "Multi-model configurations (Seq1-6)"),
                                     ("single", "b", SINGLE, "Single-model control (Seq7-9)")]:
        L = lab(e); fig, ax = plt.subplots(figsize=(7.4, 4.8))
        for sq in seqs:
            g = e[e.sequence == sq]
            ax.scatter(g.total_cost_usd, g.coverage_gain, s=14, alpha=0.55, color=COL[sq], label=L[sq])
            ax.scatter(g.total_cost_usd.mean(), g.coverage_gain.mean(), marker="X", s=140,
                       color=COL[sq], edgecolor="black", linewidth=1.0, zorder=4)
        ax.set_xlim(0, xmax); ax.set_ylim(-1, ymax)
        ax.set_xlabel("Total cost per execution (USD)"); ax.set_ylabel("Coverage gain T0→C3 (pp)")
        ax.set_title(title + " — crosses: means", loc="left")
        ax.legend(fontsize=7, loc="upper right" if arm == "multi" else "center right")
        save(fig, f"fig08{suffix}_cost_vs_coverage_{arm}")


def fig09(e):
    L = lab(e)
    c = e.groupby("sequence").agg(g=("coverage_gain", "mean"), c=("total_cost_usd", "mean"))
    c["gpu"] = c.g / c.c; c = c.reindex(SEQ_ORDER).sort_values("gpu")
    fig, ax = plt.subplots(figsize=(8.4, 4.8))
    b = ax.barh([L[i] for i in c.index], c.gpu, color=[COL[i] for i in c.index],
                hatch=["//" if i in SINGLE else None for i in c.index], edgecolor="white", height=0.62)
    ax.bar_label(b, fmt="%.1f", fontsize=8, padding=3)
    ax.set_xlabel("Coverage gain per USD (pp / $), ratio of means"); ax.set_xlim(0, c.gpu.max() * 1.15)
    ax.tick_params(axis="y", labelsize=7)
    ax.set_title("Cost efficiency per configuration (hatched: single-model control)", loc="left")
    save(fig, "fig09_cost_benefit_ranking")


def fig10(s):
    agg = (s.groupby(["arm", "model"]).agg(g=("coverage_gain", "mean"), c=("cost_usd", "mean"),
                                           t=("total_tokens", "mean")).reset_index())
    agg["gpu"] = agg.g / agg.c; agg["gpt"] = agg.g / (agg.t / 1000)
    ylim = (agg.gpu.max() * 1.25, agg.gpt.max() * 1.25); order = ["gpt5mini", "qwen", "glm"]
    for arm, suffix, title in [("multi", "a", "Multi-model configurations (Seq1-6)"),
                               ("single", "b", "Single-model control (Seq7-9)")]:
        a = agg[agg.arm == arm].set_index("model").reindex(order)
        fig, axes = plt.subplots(1, 2, figsize=(9.4, 3.9))
        for ax, col, yl, lb, fmt in [(axes[0], "gpu", ylim[0], "Coverage gain per USD (pp / $)", "%.1f"),
                                     (axes[1], "gpt", ylim[1], "Coverage gain per 1k tokens", "%.3f")]:
            b = ax.bar(order, a[col], color=[MCOL[m] for m in order], width=0.6)
            ax.bar_label(b, fmt=fmt, fontsize=8, padding=2); ax.set_ylim(0, yl); ax.set_ylabel(lb)
        fig.suptitle(f"{title}: per-model efficiency over all positions", fontsize=10.5,
                     fontweight="bold", y=1.03)
        save(fig, f"fig10{suffix}_efficiency_by_model_{arm}")


def main():
    e, s = load()
    fig03(e); fig04(e); fig05(s, e); fig06(e); fig07(e); fig08(e); fig09(e); fig10(s)


if __name__ == "__main__":
    main()
