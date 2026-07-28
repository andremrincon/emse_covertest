#!/usr/bin/env python3
"""Re-derive every reported quantity from the raw JSON and check it appears in the PDF."""
import subprocess, re, numpy as np, pandas as pd
from scipy import stats

E = pd.read_csv("/home/claude/work/executions_repaired.csv")
S = pd.read_csv("/home/claude/work/steps_repaired.csv")
txt = subprocess.run(["pdftotext","-layout",
        "/home/claude/work/paper/2025-emse-api-incremental.pdf","-"],
        capture_output=True,text=True).stdout
flat = re.sub(r"[\s\u00a0]+"," ",txt)

def dz(b,a):
    d=np.asarray(a)-np.asarray(b); return d.mean()/d.std(ddof=1)

checks=[]
def chk(name, computed, fmt="{:.2f}", must_appear=True):
    s=fmt.format(computed)
    ok = (s in flat) if must_appear else True
    checks.append((name, s, ok))

# ---- RQ1 aggregate
chk("T0 pooled", E.T0.mean())
chk("C1 pooled", E.C1.mean())
chk("C2 pooled", E.C2.mean())
chk("C3 pooled", E.C3.mean())
chk("total gain", (E.C3-E.T0).mean())
chk("relative gain (ratio of means)", (E.C3-E.T0).mean()/E.T0.mean()*100)
chk("paired d T0->C3", dz(E.T0,E.C3))

# ---- Table 3
for p,g in E.groupby("project"):
    chk(f"T3 {p} final", g.C3.mean())
    chk(f"T3 {p} abs gain", (g.C3-g.T0).mean())
    chk(f"T3 {p} rel gain", (g.C3-g.T0).mean()/g.T0.mean()*100)

# ---- Table 4
for nm,b,a in [("T0->C1",E.T0,E.C1),("C1->C2",E.C1,E.C2),
               ("C2->C3",E.C2,E.C3),("C1->C3",E.C1,E.C3)]:
    chk(f"T4 {nm} mean", (a-b).mean())
    chk(f"T4 {nm} dz", dz(b,a))
tot = (E.C1-E.T0).mean()+(E.C2-E.C1).mean()+(E.C3-E.C2).mean()
checks.append(("T4 rows sum to total gain", f"{tot:.4f}",
               abs(tot-(E.C3-E.T0).mean())<1e-9))

# ---- Table 5
pos=S.groupby("position").gain.agg(["mean","median","std","sum"])
pos["share"]=pos["sum"]/pos["sum"].sum()*100
for i in (1,2,3):
    chk(f"T5 pos{i} mean", pos.loc[i,"mean"])
    chk(f"T5 pos{i} std",  pos.loc[i,"std"])
    chk(f"T5 pos{i} share",pos.loc[i,"share"])
checks.append(("T5 means sum to total gain", f"{pos['mean'].sum():.4f}",
               abs(pos["mean"].sum()-(E.C3-E.T0).mean())<1e-9))

# ---- Table 6 / 7
r=E.groupby("seq_num").agg(C3=("C3","mean"),m=("time_min","mean"),s=("C3","std"))
for i in r.index:
    chk(f"T6 seq{i} final", r.loc[i,"C3"])
chk("T6 spread", r.C3.max()-r.C3.min())
wide=E.pivot_table(index=["project","seed","run"],columns="seq_num",values="C3")[[1,2,3,4,5,6]]
fr=stats.friedmanchisquare(*[wide[c].values for c in wide.columns])
kw=stats.kruskal(*[g.C3.values for _,g in E.groupby("seq_num")])
chk("T7 Friedman chi2", fr.statistic)
chk("T7 Friedman p", fr.pvalue, "{:.2f}")
chk("T7 Kruskal H", kw.statistic)

# ---- Table 8
c=E.groupby("seq_num").agg(cost=("cost","mean"),tok=("tokens","mean"),gain=("gain","mean"))
c["gpu"]=c.gain/c.cost
for i in c.index:
    chk(f"T8 seq{i} cost", c.loc[i,"cost"], "{:.3f}")
    chk(f"T8 seq{i} gain/$", c.loc[i,"gpu"])
chk("T8 best gain/$", c.gpu.max()); chk("T8 worst gain/$", c.gpu.min())

# ---- Table 9
m=S.groupby("model").agg(gain=("gain","mean"),cost=("cost","mean"),tok=("tokens","mean"))
m["gpu"]=m.gain/m.cost; m["gpt"]=m.gain/(m.tok/1000)
for k in m.index:
    chk(f"T9 {k} gain", m.loc[k,"gain"])
    chk(f"T9 {k} cost", m.loc[k,"cost"], "{:.3f}")
    chk(f"T9 {k} gain/$", m.loc[k,"gpu"])
    chk(f"T9 {k} gain/1ktok", m.loc[k,"gpt"], "{:.3f}")

# ---- Table 10
mp=S.groupby(["model","position"]).agg(gain=("gain","mean"),cost=("cost","mean"))
mp["gpu"]=mp.gain/mp.cost
for (k,p_) in mp.index:
    chk(f"T10 {k} pos{p_} gain", mp.loc[(k,p_),"gain"])
    chk(f"T10 {k} pos{p_} gain/$", mp.loc[(k,p_),"gpu"])

# ---- per-system C1->C3
for p,g in E.groupby("project"):
    chk(f"C1->C3 {p}", (g.C3-g.C1).mean())

fail=[c for c in checks if not c[2]]
print(f"CHECKS: {len(checks)}   PASS: {len(checks)-len(fail)}   FAIL: {len(fail)}")
if fail:
    print("\nvalues re-derived from raw data but NOT found in the PDF:")
    for n,v,_ in fail: print(f"   {n:34s} expected {v}")
