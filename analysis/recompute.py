#!/usr/bin/env python3
"""Independent recomputation of every number the paper reports."""
import numpy as np, pandas as pd
from scipy import stats
from itertools import combinations

pd.set_option("display.width", 200)
E = pd.read_csv("/home/claude/work/my_executions.csv")
S = pd.read_csv("/home/claude/work/my_steps.csv")

SEQNAME = {1:"Seq1",2:"Seq2",3:"Seq3",4:"Seq4",5:"Seq5",6:"Seq6"}
BAD = [("sequence_06","run_01",450,"features-service"),
       ("sequence_06","run_03",767,"features-service")]
badmask = E.apply(lambda r: (r.sequence,r["run"],r.seed,r.project) in BAD, axis=1)
E["flagged"] = badmask
S = S.merge(E[["sequence","run","seed","project","flagged"]],
            on=["sequence","run","seed","project"], how="left")
print("flagged executions:", E.flagged.sum(), " flagged steps:", S.flagged.sum())

Eg = E[~E.flagged].copy()      # position-safe subset (n=268)
Sg = S[~S.flagged].copy()      # 804 steps

def d_paired(b,a):
    d=np.asarray(a)-np.asarray(b); return d.mean()/d.std(ddof=1)

def boot_ci(x, n=10000, seed=0):
    rng=np.random.default_rng(seed); x=np.asarray(x)
    m=rng.choice(x,(n,len(x)),replace=True).mean(axis=1)
    return np.percentile(m,[2.5,97.5])

print("\n" + "="*78)
print("RQ1-A  AGGREGATE  (all 270; cycle totals are valid for these two)")
print("="*78)
T0,C3 = E.T0, E.C3
w,p = stats.wilcoxon(C3,T0)
print(f"T0 mean = {T0.mean():.4f}   C3 mean = {C3.mean():.4f}")
print(f"abs gain = {(C3-T0).mean():.4f} pp   rel = {((C3-T0)/T0*100).mean():.4f} %")
print(f"rel of means = {((C3.mean()-T0.mean())/T0.mean()*100):.4f} %")
print(f"Wilcoxon W={w:.1f} p={p:.3g}   paired Cohen's d = {d_paired(T0,C3):.4f}")
print(f"95% bootstrap CI of mean diff = {boot_ci(C3-T0)}")

print("\n--- pseudo-replication check: collapse the 9 replicates per (seq,project) cell ---")
cell = E.groupby(["sequence","project"], as_index=False).agg(T0=("T0","mean"),C3=("C3","mean"))
w2,p2 = stats.wilcoxon(cell.C3, cell.T0)
print(f"n cells = {len(cell)}  gain = {(cell.C3-cell.T0).mean():.4f} pp  "
      f"Wilcoxon p={p2:.3g}  d={d_paired(cell.T0,cell.C3):.4f}")
proj = E.groupby("project", as_index=False).agg(T0=("T0","mean"),C3=("C3","mean"))
print(f"n projects = {len(proj)}  gain = {(proj.C3-proj.T0).mean():.4f} pp  "
      f"Wilcoxon p={stats.wilcoxon(proj.C3,proj.T0)[1]:.4g}")

print("\n" + "="*78)
print("RQ1-B  PER-PROJECT  (paper Table 3)")
print("="*78)
pp_ = E.groupby("project").agg(T0=("T0","mean"),C3=("C3","mean"),n=("C3","size"))
pp_["abs"]=pp_.C3-pp_.T0; pp_["rel"]=pp_["abs"]/pp_.T0*100
print(pp_.round(4).to_string())
print(f"POOLED  T0={T0.mean():.2f} C3={C3.mean():.2f} abs={(C3-T0).mean():.2f} "
      f"rel={((C3.mean()-T0.mean())/T0.mean()*100):.2f}")

print("\n" + "="*78)
print("RQ1-C  STAGE CHAIN T0->C1->C2->C3   (paper Table 4)")
print("="*78)
for label,df in [("ALL 270", E), ("CLEAN 268", Eg)]:
    print(f"\n[{label}]  T0={df.T0.mean():.4f} C1={df.C1.mean():.4f} "
          f"C2={df.C2.mean():.4f} C3={df.C3.mean():.4f}")
    for nm,b,a in [("T0->C1",df.T0,df.C1),("C1->C2",df.C1,df.C2),
                   ("C2->C3",df.C2,df.C3),("C1->C3",df.C1,df.C3),("T0->C3",df.T0,df.C3)]:
        diff=a-b; _,pv=stats.wilcoxon(a,b)
        lo,hi=boot_ci(diff)
        print(f"  {nm:8s} mean={diff.mean():7.4f} dz={d_paired(b,a):6.4f} "
              f"CI=[{lo:6.3f},{hi:6.3f}] p={pv:.3g}")
    print(f"  frac gaining beyond C1: {( (df.C3-df.C1) > 1e-9).mean()*100:.1f}%")
    print(f"  frac gaining at pos3  : {( (df.C3-df.C2) > 1e-9).mean()*100:.1f}%")
    print(f"  median C1->C2={np.median(df.C2-df.C1):.4f}  C2->C3={np.median(df.C3-df.C2):.4f}")

print("\n--- C1->C3 split by leading model ---")
for label,df in [("ALL 270",E),("CLEAN 268",Eg)]:
    print(f"[{label}]")
    for m,g in df.groupby("m1"):
        _,pv=stats.wilcoxon(g.C3,g.C1)
        print(f"   lead={m:9s} n={len(g):3d} C1->C3={(g.C3-g.C1).mean():6.4f} p={pv:.3g}")

print("\n--- C1->C3 per project ---")
for label,df in [("ALL 270",E),("CLEAN 268",Eg)]:
    print(f"[{label}]")
    for pr,g in df.groupby("project"):
        _,pv=stats.wilcoxon(g.C3,g.C1) if (g.C3-g.C1).abs().sum()>0 else (np.nan,1.0)
        print(f"   {pr:22s} n={len(g):3d} C1={g.C1.mean():6.2f} C3={g.C3.mean():6.2f} "
              f"delta={(g.C3-g.C1).mean():6.4f} p={pv:.3g}")

print("\n" + "="*78)
print("RQ1-D  POSITION DECOMPOSITION  (paper Table 5)")
print("="*78)
for label,df in [("ALL 810 steps (Perplexity)",S),("CLEAN 804 steps",Sg)]:
    g=df.groupby("position")["gain"].agg(["mean","median","std","min","max","sum"])
    g["share"]=g["sum"]/g["sum"].sum()*100
    print(f"\n[{label}]  sum of position means = {g['mean'].sum():.4f}")
    print(g.round(4).to_string())

print("\n[CHAIN-BASED position gains, CLEAN 268]  (telescopes exactly to total)")
ch = pd.DataFrame({1:Eg.C1-Eg.T0, 2:Eg.C2-Eg.C1, 3:Eg.C3-Eg.C2})
cg = ch.agg(["mean","median","std"]).T
cg["share"]=ch.sum()/ch.sum().sum()*100
print(cg.round(4).to_string())
print("sum of means =", round(ch.mean().sum(),4))

print("\n" + "="*78)
print("RQ2  ORDERING  (paper Tables 6 and 7)")
print("="*78)
r = E.groupby(["seq_num","seq_label"]).agg(
        C3=("C3","mean"),gain=("gain","mean"),std=("C3","std"),
        minutes=("time_min","mean"),n=("C3","size")).reset_index()
r["Seq"]=r.seq_num.map(SEQNAME)
print(r.sort_values("C3",ascending=False).round(4).to_string(index=False))
sp=r.C3.max()-r.C3.min(); print(f"\nbest-worst spread = {sp:.4f} pp")
top=r.sort_values('C3',ascending=False)
print("within 0.4pp of best:",(top.C3>=top.C3.max()-0.4).sum(),
      " within 0.7pp:",(top.C3>=top.C3.max()-0.7).sum())

wide = E.pivot_table(index=["project","seed","run"],columns="seq_num",values="C3")[[1,2,3,4,5,6]]
fr=stats.friedmanchisquare(*[wide[c].values for c in wide.columns])
kw=stats.kruskal(*[g.C3.values for _,g in E.groupby("seq_num")])
print(f"\nFriedman chi2={fr.statistic:.4f} p={fr.pvalue:.4f}  (n blocks={len(wide)})")
print(f"Kruskal-Wallis H={kw.statistic:.4f} p={kw.pvalue:.4f}")
pv=[]
for a,b in combinations(wide.columns,2):
    d=wide[a]-wide[b]
    pv.append(1.0 if d.abs().sum()==0 else stats.wilcoxon(wide[a],wide[b])[1])
pv=np.array(pv); o=np.argsort(pv); m=len(pv); holm=np.empty(m); prev=0
for k,i in enumerate(o):
    prev=max(prev,min((m-k)*pv[i],1.0)); holm[i]=prev
print(f"15 pairwise Wilcoxon, smallest p_holm = {holm.min():.4f}  any sig: {(holm<0.05).any()}")

print("\n" + "="*78)
print("RQ3  COST  (paper Tables 8, 9, 10)")
print("="*78)
c = E.groupby(["seq_num","seq_label"]).agg(
        cost=("cost","mean"),tokens=("tokens","mean"),gain=("gain","mean")).reset_index()
c["Seq"]=c.seq_num.map(SEQNAME)
c["gain_per_usd_RATIO_OF_MEANS"]=c.gain/c.cost
mr = E.assign(r=E.gain/E.cost).groupby("seq_num").r.mean()
c["gain_per_usd_MEAN_OF_RATIOS"]=c.seq_num.map(mr)
print(c.round(4).to_string(index=False))
print(f"\ncost range: {c.cost.min():.4f} - {c.cost.max():.4f}")
print(f"token range: {c.tokens.min():.0f} - {c.tokens.max():.0f}")

print("\n--- per-model (Table 9) ---")
for label,df in [("ALL 810",S),("CLEAN 804",Sg)]:
    m=df.groupby("model").agg(gain=("gain","mean"),cost=("cost","mean"),
                              tokens=("tokens","mean"),time=("time_min","mean")).reset_index()
    m["ROM_gain_per_usd"]=m.gain/m.cost
    m["ROM_gain_per_1k_tok"]=m.gain/(m.tokens/1000)
    mor=df.assign(r=np.where(df.cost>0,df.gain/df.cost,np.nan)).groupby("model").r.mean()
    m["MOR_gain_per_usd"]=m.model.map(mor)
    print(f"[{label}]"); print(m.round(4).to_string(index=False))

print("\n--- per-model x position (Table 10), CLEAN 804 ---")
mp=Sg.groupby(["model","position"]).agg(gain=("gain","mean"),cost=("cost","mean"),
                                        tokens=("tokens","mean")).reset_index()
mp["ROM_gain_per_usd"]=mp.gain/mp.cost
mor=Sg.assign(r=Sg.gain/Sg.cost).groupby(["model","position"]).r.mean()
mp["MOR_gain_per_usd"]=[mor.loc[(a,b)] for a,b in zip(mp.model,mp.position)]
print(mp.round(4).to_string(index=False))

print("\n--- total tokens / cost of the whole campaign ---")
print(f"total cost = ${E.cost.sum():.2f}   total tokens = {E.tokens.sum():,}")
print(f"mean per-run time = {E.time_min.mean():.2f} min")
