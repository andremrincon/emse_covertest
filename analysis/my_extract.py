#!/usr/bin/env python3
"""Independent re-extraction of the 270 executions, straight from raw JSON."""
import glob, json, os, sys
import pandas as pd

ROOT = "/home/claude/work/synthesis-report"
SHORT = {"qwen/qwen3.7-plus": "qwen", "openai/gpt-5-mini": "gpt5mini", "z-ai/glm-5.2": "glm"}

files = sorted(glob.glob(os.path.join(ROOT, "sequence_*", "run_*", "*", "*", "ts01_synthesis_metrics.json")))
print("files found:", len(files))

erows, srows, anomalies = [], [], []
for f in files:
    rel = os.path.relpath(f, ROOT).split(os.sep)
    seq, run, seed, proj = rel[0], rel[1], rel[2], rel[3]
    d = json.load(open(f))
    steps = d["steps"]; tot = d["totals"]

    # sanity: is the steps array already in report_order_index order?
    roi = [s.get("report_order_index") for s in steps]
    fni = [s.get("filename_index") for s in steps]
    if roi != sorted(roi):
        anomalies.append(("steps_array_unsorted", rel, roi))
    if roi != fni:
        anomalies.append(("roi_ne_filename_index", rel, roi, fni))
    if len(steps) != 3:
        anomalies.append(("n_steps", rel, len(steps)))
    if d.get("warnings"): anomalies.append(("warnings", rel, d["warnings"]))
    if d.get("gaps"):     anomalies.append(("gaps", rel, d["gaps"]))

    order = [SHORT[s["generation_model"]] for s in steps]
    # chain continuity: step k final == step k+1 initial
    for k in range(len(steps) - 1):
        if abs(steps[k]["final_coverage"] - steps[k+1]["initial_coverage"]) > 1e-6:
            anomalies.append(("chain_break", rel, k, steps[k]["final_coverage"], steps[k+1]["initial_coverage"]))
    if abs(steps[0]["initial_coverage"] - tot["initial_coverage_cycle"]) > 1e-6:
        anomalies.append(("T0_mismatch", rel, steps[0]["initial_coverage"], tot["initial_coverage_cycle"]))
    if abs(steps[-1]["final_coverage"] - tot["final_coverage_cycle"]) > 1e-6:
        anomalies.append(("C3_mismatch", rel, steps[-1]["final_coverage"], tot["final_coverage_cycle"]))

    sgs = sum(s["coverage_gain"] for s in steps)
    if abs(sgs - tot["coverage_gain_cycle"]) > 1e-3:
        anomalies.append(("gain_reconcile", rel, sgs, tot["coverage_gain_cycle"]))

    # cost/token totals reconcile?
    ct = sum(s["llm_totals"]["cost_total"] for s in steps)
    tk = sum(s["llm_totals"]["total_tokens"] for s in steps)
    if abs(ct - tot["total_cost"]) > 1e-6: anomalies.append(("cost_reconcile", rel, ct, tot["total_cost"]))
    if tk != tot["total_tokens"]:          anomalies.append(("token_reconcile", rel, tk, tot["total_tokens"]))

    erows.append(dict(
        sequence=seq, seq_num=int(seq.split("_")[1]), run=run, seed=seed, project=proj,
        seq_label=" -> ".join(order), m1=order[0], m2=order[1], m3=order[2],
        T0=tot["initial_coverage_cycle"], C1=steps[0]["final_coverage"],
        C2=steps[1]["final_coverage"],    C3=tot["final_coverage_cycle"],
        gain=tot["coverage_gain_cycle"],
        time_min=tot["total_execution_time_seconds"]/60.0,
        tokens=tot["total_tokens"], cost=tot["total_cost"], requests=tot["total_requests"],
    ))
    for pos, s in enumerate(steps, start=1):
        srows.append(dict(
            sequence=seq, seq_num=int(seq.split("_")[1]), run=run, seed=seed, project=proj,
            position=pos, model=SHORT[s["generation_model"]],
            init=s["initial_coverage"], fin=s["final_coverage"], gain=s["coverage_gain"],
            tokens=s["llm_totals"]["total_tokens"], cost=s["llm_totals"]["cost_total"],
            requests=s["llm_totals"]["total_requests"],
            time_min=s["execution_time_seconds"]/60.0,
        ))

E = pd.DataFrame(erows); S = pd.DataFrame(srows)
E.to_csv("/home/claude/work/my_executions.csv", index=False)
S.to_csv("/home/claude/work/my_steps.csv", index=False)

print("executions:", len(E), " steps:", len(S))
print("\n--- ANOMALIES IN RAW DATA ---")
if not anomalies:
    print("none")
else:
    from collections import Counter
    print(Counter(a[0] for a in anomalies))
    for a in anomalies[:15]: print(" ", a)

print("\n--- design completeness ---")
print(E.groupby(["sequence"]).size().to_string())
print("\ncells (seq x proj x seed x run) unique:", len(E.drop_duplicates(["sequence","project","seed","run"])))
print("\nsequence -> model order mapping observed:")
print(E.groupby(["sequence","seq_label"]).size().to_string())
