#!/usr/bin/env python3
"""Definitive analysis: repair the 2 mis-logged records, recompute everything,
and run a sensitivity check under exclusion."""
import numpy as np, pandas as pd
from scipy import stats
from itertools import combinations
import json, os

pd.set_option("display.width", 220)
E = pd.read_csv("/home/claude/work/my_executions.csv")
S = pd.read_csv("/home/claude/work/my_steps.csv")
SEQN = {1:"Seq1",2:"Seq2",3:"Seq3",4:"Seq4",5:"Seq5",6:"Seq6"}
BAD = [("sequence_06","run_01",450,"features-service"),
       ("sequence_06","run_03",767,"features-service")]

def is_bad(r): return (r.sequence, r["run"], r.seed, r.project) in BAD
E["flagged"] = E.apply(is_bad, axis=1)
S = S.merge(E[["sequence","run","seed","project","flagged"]],
            on=["sequence","run","seed","project"], how="left")

# ---------- REPAIR ----------
# 7/7 sibling runs of seq06/features-service give glm@pos1 exactly 56.02 -> 80.96.
Sr = S.copy()
m = Sr.flagged & (Sr.position == 1)
Sr.loc[m, "fin"]  = 80.96
Sr.loc[m, "gain"] = 80.96 - 56.02
Er = E.copy()
Er.loc[Er.flagged, "C1"] = 80.96          # C2, C3 already 80.96; T0, gain untouched

def dz(b,a):
    d=np.asarray(a)-np.asarray(b); return d.mean()/d.std(ddof=1)
def bci(x,n=10000,seed=0):
    rng=np.random.default_rng(seed); x=np.asarray(x)
    return np.percentile(rng.choice(x,(n,len(x)),replace=True).mean(axis=1),[2.5,97.5])
def holm(p):
    p=np.asarray(p); o=np.argsort(p); m=len(p); out=np.empty(m); prev=0
    for k,i in enumerate(o):
        prev=max(prev,min((m-k)*p[i],1.0)); out[i]=prev
    return out

OUT={}
print("#"*80); print("# CORRECTED RESULTS (repaired, n=270 / 810)"); print("#"*80)

# ---------------- Table 2 ----------------
t2 = E.groupby("project").T0.first().sort_index()
print("\n[Table 2] T0 per SUT"); print(t2.to_string())
print("pooled T0 =", round(E.T0.mean(),4), "| unweighted mean of 5 =", round(t2.mean(),4))

# ---------------- Table 3 ----------------
print("\n[Table 3] per-project")
t3=Er.groupby("project").agg(T0=("T0","mean"),C3=("C3","mean"))
t3["abs"]=t3.C3-t3.T0; t3["rel"]=t3["abs"]/t3.T0*100
print(t3.round(4).to_string())
pool_abs=(Er.C3-Er.T0).mean(); pool_rel=pool_abs/Er.T0.mean()*100
print(f"POOLED abs={pool_abs:.4f}  rel(ratio of means)={pool_rel:.4f}  "
      f"rel(mean of ratios)={((Er.C3-Er.T0)/Er.T0*100).mean():.4f}")
OUT["t3"]=t3

# ---------------- Table 4 ----------------
print("\n[Table 4] stage chain (repaired)")
rows=[]; praw=[]
for nm,b,a in [("T0->C1",Er.T0,Er.C1),("C1->C2",Er.C1,Er.C2),("C2->C3",Er.C2,Er.C3),
               ("C1->C3",Er.C1,Er.C3),("T0->C3",Er.T0,Er.C3)]:
    d=a-b; _,p=stats.wilcoxon(a,b); lo,hi=bci(d)
    rows.append(dict(cmp=nm,before=b.mean(),after=a.mean(),diff=d.mean(),
                     dz=dz(b,a),lo=lo,hi=hi,p=p))
    praw.append(p)
t4=pd.DataFrame(rows); t4["p_holm"]=holm(praw)
print(t4.round(6).to_string(index=False))
print("check: T0->C1 + C1->C2 + C2->C3 =", round(t4.iloc[0]['diff']+t4.iloc[1]['diff']+t4.iloc[2]['diff'],6))
print("frac gaining beyond C1:", round(((Er.C3-Er.C1)>1e-9).mean()*100,1),
      "| frac gaining at pos3:", round(((Er.C3-Er.C2)>1e-9).mean()*100,1))
print("median C1->C2 =",np.median(Er.C2-Er.C1)," C2->C3 =",np.median(Er.C3-Er.C2))
OUT["t4"]=t4

print("\n  C1->C3 by leading model:")
for mm,g in Er.groupby("m1"):
    _,p=stats.wilcoxon(g.C3,g.C1); print(f"   {mm:9s} n={len(g)} delta={(g.C3-g.C1).mean():.4f} p={p:.3g}")
print("\n  C1->C3 per project:")
pp=[]
for pr,g in Er.groupby("project"):
    _,p=stats.wilcoxon(g.C3,g.C1); pp.append(p)
    print(f"   {pr:22s} C1={g.C1.mean():6.2f} C3={g.C3.mean():6.2f} delta={(g.C3-g.C1).mean():7.4f} p={p:.3g}")
print("   max p over the 5 =", f"{max(pp):.3g}")

# ---------------- Table 5 ----------------
print("\n[Table 5] position decomposition (repaired, 810 steps)")
t5=Sr.groupby("position").gain.agg(["mean","median","std","sum"])
t5["share"]=t5["sum"]/t5["sum"].sum()*100
print(t5.round(4).to_string())
print("sum of means =",round(t5["mean"].sum(),4),"| total gain =",round(pool_abs,4))
print("share via mean/total:", (t5["mean"]/pool_abs*100).round(2).to_dict())
OUT["t5"]=t5

# ---------------- Table 6/7 ----------------
print("\n[Table 6] ordering")
t6=Er.groupby(["seq_num","seq_label"]).agg(C3=("C3","mean"),gain=("gain","mean"),
        std=("C3","std"),minutes=("time_min","mean")).reset_index()
t6["Seq"]=t6.seq_num.map(SEQN); t6=t6.sort_values("C3",ascending=False)
print(t6.round(4).to_string(index=False))
print("spread =",round(t6.C3.max()-t6.C3.min(),4))
srt=t6.C3.sort_values(ascending=False).values
print("top4 span =",round(srt[0]-srt[3],4)," top5 span =",round(srt[0]-srt[4],4))
OUT["t6"]=t6

wide=Er.pivot_table(index=["project","seed","run"],columns="seq_num",values="C3")[[1,2,3,4,5,6]]
fr=stats.friedmanchisquare(*[wide[c].values for c in wide.columns])
kw=stats.kruskal(*[g.C3.values for _,g in Er.groupby("seq_num")])
pv=[1.0 if (wide[a]-wide[b]).abs().sum()==0 else stats.wilcoxon(wide[a],wide[b])[1]
    for a,b in combinations(wide.columns,2)]
hp=holm(pv)
print(f"\n[Table 7] Friedman chi2={fr.statistic:.4f} p={fr.pvalue:.4f} blocks={len(wide)}")
print(f"          Kruskal H={kw.statistic:.4f} p={kw.pvalue:.4f}")
print(f"          smallest p_holm = {hp.min():.4f}   any significant: {(hp<0.05).any()}")
OUT["t7"]=dict(fr=fr,kw=kw,holm_min=hp.min())

# ---------------- Table 8 ----------------
print("\n[Table 8] cost per ordering (ratio of means)")
t8=Er.groupby(["seq_num","seq_label"]).agg(cost=("cost","mean"),tokens=("tokens","mean"),
        gain=("gain","mean"),minutes=("time_min","mean")).reset_index()
t8["Seq"]=t8.seq_num.map(SEQN)
t8["gain_per_usd"]=t8.gain/t8.cost
t8["gain_per_1k_tok"]=t8.gain/(t8.tokens/1000)
t8=t8.sort_values("gain_per_usd",ascending=False)
print(t8.round(4).to_string(index=False))
print(f"cost range {t8.cost.min():.4f}-{t8.cost.max():.4f} | tokens {t8.tokens.min():.0f}-{t8.tokens.max():.0f}")
OUT["t8"]=t8

# ---------------- Table 9 ----------------
print("\n[Table 9] per model (repaired, 810 steps, ratio of means)")
t9=Sr.groupby("model").agg(gain=("gain","mean"),cost=("cost","mean"),
        tokens=("tokens","mean"),minutes=("time_min","mean")).reset_index()
t9["gain_per_usd"]=t9.gain/t9.cost
t9["gain_per_1k_tok"]=t9.gain/(t9.tokens/1000)
t9=t9.sort_values("gain_per_usd",ascending=False)
print(t9.round(4).to_string(index=False))
print("  (paper reported mean-of-ratios: glm 287.19, qwen 93.98, gpt5mini 90.33)")
OUT["t9"]=t9

# ---------------- Table 10 ----------------
print("\n[Table 10] model x position (repaired, ratio of means)")
t10=Sr.groupby(["model","position"]).agg(gain=("gain","mean"),cost=("cost","mean"),
        tokens=("tokens","mean")).reset_index()
t10["gain_per_usd"]=t10.gain/t10.cost
print(t10.round(4).to_string(index=False))
OUT["t10"]=t10

# ---------------- zero-cost steps ----------------
z=Sr[Sr.cost==0]
print(f"\n[no-op steps] {len(z)} of {len(Sr)} steps made no LLM call "
      f"(all spring-actuator-demo already at 100%): "
      f"{z.groupby('position').size().to_dict()}")

# ---------------- campaign totals ----------------
print(f"\n[campaign] total cost ${Er.cost.sum():.2f} | tokens {Er.tokens.sum():,} | "
      f"mean run {Er.time_min.mean():.2f} min | total compute {Er.time_min.sum()/60:.1f} h")

# ---------------- SENSITIVITY: exclusion instead of repair ----------------
print("\n"+"#"*80); print("# SENSITIVITY: drop the 2 flagged executions (n=268 / 804)"); print("#"*80)
Ee=E[~E.flagged]; Se=S[~S.flagged]
print(f"T0={Ee.T0.mean():.4f} C1={Ee.C1.mean():.4f} C2={Ee.C2.mean():.4f} C3={Ee.C3.mean():.4f}")
print(f"T0->C3={(Ee.C3-Ee.T0).mean():.4f} dz={dz(Ee.T0,Ee.C3):.4f}")
print(f"C1->C3={(Ee.C3-Ee.C1).mean():.4f} dz={dz(Ee.C1,Ee.C3):.4f} p={stats.wilcoxon(Ee.C3,Ee.C1)[1]:.3g}")
pe=Se.groupby("position").gain.mean(); print("positions:",pe.round(4).to_dict()," sum=",round(pe.sum(),4))
we=Ee.pivot_table(index=["project","seed","run"],columns="seq_num",values="C3")[[1,2,3,4,5,6]].dropna()
fre=stats.friedmanchisquare(*[we[c].values for c in we.columns])
print(f"Friedman p={fre.pvalue:.4f} (blocks={len(we)})")
me=Se.groupby("model").agg(g=("gain","mean"),c=("cost","mean"))
print("gain/$ ROM:",(me.g/me.c).round(2).to_dict())

# ---------------- pseudo-replication ----------------
print("\n"+"#"*80); print("# UNIT-OF-ANALYSIS CHECK"); print("#"*80)
cell=Er.groupby(["sequence","project"],as_index=False).agg(T0=("T0","mean"),C1=("C1","mean"),C3=("C3","mean"))
print(f"cell means (n=30): T0->C3={(cell.C3-cell.T0).mean():.4f} p={stats.wilcoxon(cell.C3,cell.T0)[1]:.3g} dz={dz(cell.T0,cell.C3):.4f}")
print(f"                   C1->C3={(cell.C3-cell.C1).mean():.4f} p={stats.wilcoxon(cell.C3,cell.C1)[1]:.3g} dz={dz(cell.C1,cell.C3):.4f}")

with open("/home/claude/work/corrected.json","w") as f:
    json.dump({k:(v.to_dict() if hasattr(v,"to_dict") else str(v)) for k,v in OUT.items()},f,indent=1,default=str)
Sr.to_csv("/home/claude/work/steps_repaired.csv",index=False)
Er.to_csv("/home/claude/work/executions_repaired.csv",index=False)
print("\nsaved repaired datasets")
