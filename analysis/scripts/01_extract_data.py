#!/usr/bin/env python3
"""
01_extract_data.py
==================
Extract and flatten all 270+135 ts01_synthesis_metrics.json executions from the
EMSE experiment into tidy CSV files for downstream analysis (RQ1-RQ3).

Experimental design:
  6 sequences (model-order permutations) x 3 runs x 3 seeds x 5 projects = 270 executions.
  3 sequences (model-order no-permutations) x 3 runs x 3 seeds x 5 projects = 135 executions
Each execution has 3 incremental model-generation steps.

Outputs (in ../data/):
  - executions.csv : one row per execution (270 rows) - cycle-level totals.
  - steps.csv      : one row per model step  (810 rows) - per-model detail.
  - data_quality_flags.csv : executions where per-step gains do not reconcile
                             with the cycle total (logging anomalies).

Usage:
  python3 01_extract_data.py [--root <emse_data_root>] [--out <output_dir>]
"""
import argparse
import glob
import json
import os
import sys
import csv

# Canonical model-order map (from experiment description / model_sequence file).
MODEL_SEQUENCE = {
    "sequence_01": ["qwen/qwen3.7-plus", "openai/gpt-5-mini", "z-ai/glm-5.2"],
    "sequence_02": ["qwen/qwen3.7-plus", "z-ai/glm-5.2", "openai/gpt-5-mini"],
    "sequence_03": ["openai/gpt-5-mini", "qwen/qwen3.7-plus", "z-ai/glm-5.2"],
    "sequence_04": ["openai/gpt-5-mini", "z-ai/glm-5.2", "qwen/qwen3.7-plus"],
    "sequence_05": ["z-ai/glm-5.2", "qwen/qwen3.7-plus", "openai/gpt-5-mini"],
    "sequence_06": ["z-ai/glm-5.2", "openai/gpt-5-mini", "qwen/qwen3.7-plus"],
    "sequence_07": ["z-ai/glm-5.2", "z-ai/glm-5.2", "z-ai/glm-5.2"],
    "sequence_08": ["qwen/qwen3.7-plus", "qwen/qwen3.7-plus", "qwen/qwen3.7-plus"],
    "sequence_09": ["openai/gpt-5-mini", "openai/gpt-5-mini", "openai/gpt-5-mini"],
}

# Short, human-friendly model labels.
MODEL_SHORT = {
    "qwen/qwen3.7-plus": "qwen",
    "openai/gpt-5-mini": "gpt5mini",
    "z-ai/glm-5.2": "glm",
}

PROJECTS = ["features-service", "ncs", "scs", "restcountries", "spring-actuator-demo"]
SEEDS = ["367", "450", "767"]
EPS = 1e-6


def find_default_root():
    """Locate the emse data root by searching for the metrics files."""
    candidates = [
        "/home/user/workspace/emse_extract",
        "/home/user/workspace/emse_data",
        os.path.join(os.path.dirname(__file__), "..", "..", "emse_extract"),
    ]
    for c in candidates:
        if glob.glob(os.path.join(c, "sequence_*", "run_*", "*", "*",
                                  "ts01_synthesis_metrics.json")):
            return os.path.abspath(c)
    return None


def parse_path(fpath, root):
    """Extract (sequence, run, seed, project) from a metrics file path."""
    rel = os.path.relpath(fpath, root)
    parts = rel.split(os.sep)
    # sequence_0X / run_0X / <seed> / <project> / ts01_synthesis_metrics.json
    seq, run, seed, project = parts[0], parts[1], parts[2], parts[3]
    return seq, run, seed, project


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--root", default=None, help="Root of emse data tree")
    ap.add_argument("--out", default=os.path.join(os.path.dirname(__file__), "..", "data"))
    args = ap.parse_args()

    root = args.root or find_default_root()
    if not root or not os.path.isdir(root):
        sys.exit("ERROR: could not locate emse data root. Pass --root.")
    out = os.path.abspath(args.out)
    os.makedirs(out, exist_ok=True)

    files = sorted(glob.glob(os.path.join(root, "sequence_*", "run_*", "*", "*",
                                          "ts01_synthesis_metrics.json")))
    print(f"Found {len(files)} metrics files under {root}")

    exec_rows = []
    step_rows = []
    dq_rows = []

    for f in files:
        seq, run, seed, project = parse_path(f, root)
        with open(f) as fh:
            d = json.load(fh)

        steps = d["steps"]
        totals = d["totals"]
        rankings = d.get("rankings", {})

        seq_num = int(seq.split("_")[1])
        run_num = int(run.split("_")[1])
        model_order = [s["generation_model"] for s in steps]
        model_order_short = [MODEL_SHORT.get(m, m) for m in model_order]
        seq_label = " -> ".join(model_order_short)

        # ---- cycle-level (execution) row ----
        # Sum per-step gains and compare to cycle total (reconciliation check).
        step_gain_sum = sum(s["coverage_gain"] for s in steps)
        cycle_gain = totals["coverage_gain_cycle"]
        reconciles = abs(step_gain_sum - cycle_gain) < 1e-3

        exec_row = {
            "sequence": seq,
            "sequence_num": seq_num,
            "sequence_label": seq_label,
            "run": run,
            "run_num": run_num,
            "seed": seed,
            "project": project,
            "model_1": model_order_short[0],
            "model_2": model_order_short[1],
            "model_3": model_order_short[2],
            "initial_coverage": totals["initial_coverage_cycle"],
            "final_coverage": totals["final_coverage_cycle"],
            "coverage_gain": cycle_gain,
            "total_execution_time_seconds": totals["total_execution_time_seconds"],
            "total_execution_time_minutes": totals["total_execution_time_seconds"] / 60.0,
            "total_tokens": totals["total_tokens"],
            "total_requests": totals["total_requests"],
            "total_cost_usd": totals["total_cost"],
            "coverage_gain_per_1k_tokens": (cycle_gain / (totals["total_tokens"] / 1000.0))
                                            if totals["total_tokens"] else 0.0,
            "coverage_gain_per_usd": (cycle_gain / totals["total_cost"])
                                      if totals["total_cost"] else 0.0,
            "coverage_gain_per_minute": (cycle_gain / (totals["total_execution_time_seconds"] / 60.0))
                                         if totals["total_execution_time_seconds"] else 0.0,
            "best_coverage_gain_step": rankings.get("best_coverage_gain_step", ""),
            "best_cost_efficiency_step": rankings.get("best_cost_efficiency_step", ""),
            "best_time_efficiency_step": rankings.get("best_time_efficiency_step", ""),
            "step_gain_sum": step_gain_sum,
            "gains_reconcile": reconciles,
            "source_file": os.path.relpath(f, root),
        }
        exec_rows.append(exec_row)

        if not reconciles:
            dq_rows.append({
                "source_file": os.path.relpath(f, root),
                "sequence": seq, "run": run, "seed": seed, "project": project,
                "cycle_coverage_gain": cycle_gain,
                "step_gain_sum": step_gain_sum,
                "difference": cycle_gain - step_gain_sum,
                "note": "Sum of per-step gains does not match cycle coverage gain "
                        "(step-level attribution anomaly).",
            })

        # ---- step-level rows ----
        for pos, s in enumerate(steps):  # pos is 0-based generation order
            model = s["generation_model"]
            eff = s.get("efficiency", {})
            llm = s.get("llm_totals", {})
            step_rows.append({
                "sequence": seq,
                "sequence_num": seq_num,
                "sequence_label": seq_label,
                "run": run,
                "run_num": run_num,
                "seed": seed,
                "project": project,
                "step_position": pos + 1,          # 1,2,3 = execution order
                "model": MODEL_SHORT.get(model, model),
                "model_full": model,
                "initial_coverage": s["initial_coverage"],
                "final_coverage": s["final_coverage"],
                "coverage_gain": s["coverage_gain"],
                "execution_time_seconds": s["execution_time_seconds"],
                "execution_time_minutes": s["execution_time_seconds"] / 60.0,
                "total_tokens": llm.get("total_tokens", ""),
                "total_requests": llm.get("total_requests", ""),
                "cost_usd": llm.get("cost_total", ""),
                "coverage_gain_per_1k_tokens": eff.get("coverage_gain_per_1k_tokens", ""),
                "coverage_gain_per_usd": eff.get("coverage_gain_per_cost_unit", ""),
                "coverage_gain_per_minute": eff.get("coverage_gain_per_minute", ""),
                "source_metrics_file": s.get("source_metrics_file", ""),
            })

    # ---- write CSVs ----
    def write_csv(path, rows, fieldnames):
        with open(path, "w", newline="") as fh:
            w = csv.DictWriter(fh, fieldnames=fieldnames)
            w.writeheader()
            w.writerows(rows)
        print(f"  wrote {path}  ({len(rows)} rows)")

    write_csv(os.path.join(out, "executions.csv"), exec_rows, list(exec_rows[0].keys()))
    write_csv(os.path.join(out, "steps.csv"), step_rows, list(step_rows[0].keys()))
    if dq_rows:
        write_csv(os.path.join(out, "data_quality_flags.csv"), dq_rows, list(dq_rows[0].keys()))
    else:
        # still write an empty file with headers for reproducibility
        with open(os.path.join(out, "data_quality_flags.csv"), "w") as fh:
            fh.write("source_file,sequence,run,seed,project,cycle_coverage_gain,"
                     "step_gain_sum,difference,note\n")
        print("  no data-quality flags (all executions reconcile)")

    print("\nExtraction complete.")
    print(f"  executions: {len(exec_rows)}  |  steps: {len(step_rows)}  |  "
          f"dq_flags: {len(dq_rows)}")


if __name__ == "__main__":
    main()
