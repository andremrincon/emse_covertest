#!/usr/bin/env python3
"""Regenerate the figures affected by the data corrections, in the original style."""
import os
import numpy as np, pandas as pd
import matplotlib
matplotlib.use("Agg")
import matplotlib.pyplot as plt

FIG = "/home/claude/work/newfigs"; os.makedirs(FIG, exist_ok=True)
plt.style.use("seaborn-v0_8-whitegrid")
plt.rcParams.update({"figure.dpi":150,"savefig.dpi":300,"font.size":11,
                     "axes.titlesize":13,"axes.titleweight":"bold",
                     "axes.labelsize":11,"legend.fontsize":9,"font.family":"DejaVu Sans"})
PALETTE=["#20808D","#A84B2F","#1B474D","#FFC553","#944454","#848456"]
MODEL_COLORS={"qwen":"#20808D","gpt5mini":"#A84B2F","glm":"#FFC553"}

E=pd.read_csv("/home/claude/work/executions_repaired.csv")
S=pd.read_csv("/home/claude/work/steps_repaired.csv")
LBL={1:"qwen -> gpt5mini -> glm",2:"qwen -> glm -> gpt5mini",3:"gpt5mini -> qwen -> glm",
     4:"gpt5mini -> glm -> qwen",5:"glm -> qwen -> gpt5mini",6:"glm -> gpt5mini -> qwen"}

def save(fig,name):
    for ext in ("pdf","png"):
        fig.savefig(os.path.join(FIG,f"{name}.{ext}"),bbox_inches="tight")
    plt.close(fig); print("  wrote",name)

# ---------- fig01: coverage evolution ----------
fig,ax=plt.subplots(figsize=(8,5))
for i,(sn,g) in enumerate(E.groupby("seq_num")):
    y=[g.T0.mean(),g.C1.mean(),g.C2.mean(),g.C3.mean()]
    ax.plot([0,1,2,3],y,marker="o",color=PALETTE[i],label=LBL[sn],lw=1.6,ms=5)
    ax.annotate(f"{y[3]:.1f}",(3,y[3]),textcoords="offset points",xytext=(6,0),
                fontsize=7,color=PALETTE[i],va="center")
ax.set_xticks([0,1,2,3]); ax.set_xticklabels(["T0 (initial)","after model 1","after model 2","after model 3"])
ax.set_ylabel("Mean instruction coverage (%)")
ax.set_title("Incremental coverage evolution per sequence\nMost gain arrives with the first model; later models add little",loc="left")
ax.legend(title="Model order",fontsize=8,title_fontsize=8,loc="lower right")
save(fig,"fig01_coverage_evolution")

# ---------- fig02: initial vs final ----------
fig,ax=plt.subplots(figsize=(9,5))
sq=sorted(E.seq_num.unique()); x=np.arange(len(sq)); w=0.38
ini=[E[E.seq_num==s].T0.mean() for s in sq]
fin=[E[E.seq_num==s].C3.mean() for s in sq]
err=[E[E.seq_num==s].C3.std() for s in sq]
ax.bar(x-w/2,ini,w,label="Initial (T0)",color="#B7D5D9")
b=ax.bar(x+w/2,fin,w,yerr=err,capsize=3,label="Final (after 3 models)",color="#20808D")
ax.bar_label(b,fmt="%.1f",fontsize=7,padding=1)
ax.set_xticks(x); ax.set_xticklabels([LBL[s] for s in sq],rotation=18,ha="right",fontsize=8)
ax.set_ylabel("Instruction coverage (%)"); ax.set_ylim(0,108)
ax.set_title("Initial vs. final coverage per sequence (mean across 45 executions)\n"
             "Incremental strategy lifts coverage from ~71% to ~90% on average",loc="left")
ax.legend(loc="lower right",fontsize=8)
save(fig,"fig02_initial_vs_final")

# ---------- fig03: gain by position ----------
pos=S.groupby("position").gain.agg(["mean","std"])
fig,ax=plt.subplots(figsize=(7,4.6))
cols=["#20808D","#5FA3AC","#9FC6CC"]
b=ax.bar([1,2,3],pos["mean"],yerr=pos["std"],capsize=5,color=cols,width=.62)
for i,(xx,v) in enumerate(zip([1,2,3],pos["mean"])):
    ax.text(xx,v+0.45,f"{v:.2f} pp",ha="center",fontsize=9)
ax.set_xticks([1,2,3]); ax.set_xticklabels(["Model 1\n(first)","Model 2","Model 3\n(last)"])
ax.set_ylabel("Mean marginal coverage gain (pp)")
ax.set_title("Diminishing returns across incremental positions\n"
             "Position 1 delivers ~81% of the total coverage gain",loc="left")
save(fig,"fig03_gain_by_position")

# ---------- fig07: per-model efficiency (ratio of means) ----------
m=S.groupby("model").agg(gain=("gain","mean"),cost=("cost","mean"),tok=("tokens","mean"))
m["gpu"]=m.gain/m.cost; m["gpt"]=m.gain/(m.tok/1000)
order=["gpt5mini","qwen","glm"]
fig,axes=plt.subplots(1,2,figsize=(10,4.2))
for ax,col,ttl,ylab,fmt in [
    (axes[0],"gpu","Cost efficiency (gain per USD)","Coverage gain per USD (pp / $)","%.1f"),
    (axes[1],"gpt","Token efficiency (gain per 1k tokens)","Coverage gain per 1k tokens (pp / 1k)","%.3f")]:
    v=[m.loc[k,col] for k in order]
    bb=ax.bar(order,v,color=[MODEL_COLORS[k] for k in order],width=.6)
    ax.bar_label(bb,fmt=fmt,fontsize=9,padding=2)
    ax.set_title(ttl,fontsize=11); ax.set_ylabel(ylab)
    ax.set_ylim(0,max(v)*1.22)
fig.suptitle("Per-model efficiency (ratio of mean gain to mean cost, all positions)",
             fontsize=12,fontweight="bold",y=1.02)
save(fig,"fig07_efficiency_by_model")

# ---------- fig08: gain per dollar by ordering ----------
c=E.groupby("seq_num").agg(gain=("gain","mean"),cost=("cost","mean"))
c["gpu"]=c.gain/c.cost; c=c.sort_values("gpu")
fig,ax=plt.subplots(figsize=(8,4.4))
bb=ax.barh([LBL[i] for i in c.index],c.gpu,color="#20808D",height=.62)
ax.bar_label(bb,fmt="%.1f",fontsize=9,padding=3)
ax.set_xlabel("Coverage gain per USD (pp / $)")
ax.set_xlim(0,c.gpu.max()*1.15)
ax.set_title("Cost efficiency per ordering (RQ3)\n"
             "Differences are small; no ordering is a strong cost winner",loc="left")
save(fig,"fig08_cost_benefit_ranking")
print("done")
