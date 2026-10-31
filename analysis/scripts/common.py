"""Shared loading / preparation for the EMSE analysis (405 executions).

Arms
----
multi  : sequence_01..06, all six permutations of {qwen, gpt5mini, glm}
single : sequence_07..09, one model repeated at the three positions
         (07 = glm x3, 08 = qwen x3, 09 = gpt5mini x3)

Data repair
-----------
Two records of sequence_06 / features-service (run_01 seed 450 and run_03
seed 767) hold a valid cycle total (56.02 -> 80.96) but log a zero gain at all
three stages. In the other seven runs of that ordering and system the first
stage gains exactly 24.94 pp, so the increment is reassigned to position 1.
The raw CSV files are never modified; the repair lives only here, and
02_analysis.py reports a sensitivity analysis that excludes the two records.
"""
import os
import numpy as np
import pandas as pd

HERE = os.path.dirname(os.path.abspath(__file__))
DATA = os.path.join(HERE, "..", "data")
KEY = ["sequence", "run", "seed", "project"]
BLOCK = ["project", "seed", "run"]
MODELS = ["qwen", "gpt5mini", "glm"]
MULTI = [f"sequence_0{i}" for i in range(1, 7)]
SINGLE = ["sequence_07", "sequence_08", "sequence_09"]
SEQ_ORDER = MULTI + SINGLE
SEQ_TAG = {f"sequence_0{i}": f"Seq{i}" for i in range(1, 10)}
REPAIRED = [("sequence_06", "run_01", 450, "features-service"),
            ("sequence_06", "run_03", 767, "features-service")]
STALL_PP = 1.0
# Six features-service executions in which COVERTEST's pipeline failed (see
# 02_analysis.py, sensitivity_pipeline_failures): stage 1 aborted at the global
# compile gate because one glm-generated class imported the legacy package
# com.jayway.restassured, and its untreated classes then blocked the global
# execution gate of the later stages. Data are NOT removed; the flag is used
# only for the sensitivity analysis.
PIPELINE_FAILURES = [("sequence_05", "run_01", 367), ("sequence_05", "run_02", 367),
                     ("sequence_05", "run_02", 767), ("sequence_07", "run_01", 767),
                     ("sequence_07", "run_02", 367), ("sequence_07", "run_02", 450)]   # a stage "stalls" when it adds less than 1 pp


def _flag(df):
    return df.apply(lambda r: (r["sequence"], r["run"], r["seed"], r["project"]) in REPAIRED, axis=1)


def load(repair=True, exclude_repaired=False):
    e = pd.read_csv(os.path.join(DATA, "executions.csv"))
    s = pd.read_csv(os.path.join(DATA, "steps.csv"))
    e["repaired"] = _flag(e)
    s["repaired"] = _flag(s)
    if exclude_repaired:
        e = e[~e.repaired].copy()
        s = s[~s.repaired].copy()
    elif repair:
        m = s.repaired & (s.step_position == 1)
        s.loc[m, "final_coverage"] = 80.96
        s.loc[m, "coverage_gain"] = round(80.96 - 56.02, 2)
    chain = (s.pivot_table(index=KEY, columns="step_position", values="final_coverage")
               .reset_index())
    chain.columns = KEY + ["C1", "C2", "C3"]
    e = e.merge(chain, on=KEY, how="left")
    e["T0"] = e["initial_coverage"]
    e["arm"] = np.where(e.sequence.isin(MULTI), "multi", "single")
    e["tag"] = e.sequence.map(SEQ_TAG)
    e["gain_T0C1"] = e.C1 - e.T0
    e["gain_C1C3"] = e.C3 - e.C1
    e["stall1"] = e.gain_T0C1 < STALL_PP
    e["pipeline_failure"] = e.apply(
        lambda r: r.project == "features-service" and (r.sequence, r["run"], r.seed) in PIPELINE_FAILURES, axis=1)
    s["arm"] = np.where(s.sequence.isin(MULTI), "multi", "single")
    return e, s
