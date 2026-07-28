# Replication Package — Incremental Multi-Model LLM Strategy for Automated Open-Box Integration Testing of REST APIs

This repository holds the raw data, the generated test suites, the subject systems and the analysis scripts of the empirical study reported in:

> A. M. Rincon and A. M. R. Vincenzi. *Incremental Multi-Model LLM Strategy for Automated Open-Box Integration Testing of REST APIs.* Submitted to **Empirical Software Engineering** (Special Issue).

The tool that produced these data, **COVERTEST**, lives in a separate repository:

**➡ [github.com/aurimrv/COVERTEST](https://github.com/aurimrv/COVERTEST)**

Everything below is data and analysis. If you want to *run* the pipeline on your own subject, go to the tool repository instead.

---

## 1. What is in here

The study evaluates an incremental, coverage-guided pipeline that starts from an EvoMaster baseline suite (T0) and applies three LLMs in sequence, each targeting the instructions still uncovered by the previous stage. The full factorial design is:

| Factor | Levels | Values |
|---|---|---|
| Model ordering | 6 | all permutations of `qwen3.7-plus`, `gpt-5-mini`, `glm-5.2` |
| Subject system | 5 | `features-service`, `ncs`, `restcountries`, `scs`, `spring-actuator-demo` |
| Random seed | 3 | 367, 450, 767 |
| Repetition | 3 | `run_01`, `run_02`, `run_03` |

6 × 5 × 3 × 3 = **270 independent executions**, each comprising three generation stages, for **810 stages** in total. Every execution is preserved in full: generated sources, compiled classes, JaCoCo reports, Surefire reports, LLM traces and metrics.

**Campaign totals:** US$ 131.16, 128,231,475 tokens, 10,740 API requests, 92.3 hours of pipeline execution (excluding EvoMaster baseline generation).

---

## 2. Repository structure

```
emse_covertest/
├── analysis/                    # scripts + curated datasets (Section 5 of the paper)
│   ├── my_extract.py            # raw JSON  ->  flat CSV
│   ├── recompute.py             # independent recomputation of every reported quantity
│   ├── final_analysis.py        # definitive analysis + sensitivity checks
│   ├── refigure.py              # regenerates the figures of Section 5
│   ├── validate.py              # cross-checks the compiled PDF against the raw data
│   ├── executions_repaired.csv  # 270 rows, one per execution
│   └── steps_repaired.csv       # 810 rows, one per generation stage
│
├── generated_tests/             # RAW DATA: one folder per execution
│   ├── model_sequence           # ordering of the generation models per sequence
│   └── sequence_01 .. sequence_06/
│       └── run_01 .. run_03/
│           └── 367 | 450 | 767/             # random seed
│               └── <subject-system>/
│                   ├── ts01_synthesis_metrics.json          # ← the execution record
│                   ├── 00_ts01<model>_metrics.json          # stage 1 metrics
│                   ├── 01_ts01<model>_metrics.json          # stage 2 metrics
│                   ├── 02_ts01<model>_metrics.json          # stage 3 metrics
│                   ├── *_divergency_report.md               # assertions changed during repair
│                   ├── llm_debug/                           # full prompt/response traces
│                   ├── src/test/java/                       # the generated JUnit suite
│                   ├── target/site/jacoco/                  # coverage report (xml, csv, html)
│                   ├── target/surefire-reports/             # test execution results
│                   ├── pom.xml
│                   └── README.md
│
├── projects/                    # the five subject systems (Web Fuzzing Dataset)
│   └── <subject-system>/
│       ├── <name>.json                  # original OpenAPI specification
│       ├── <name>_enhanced.json         # enriched specification used by the pipeline
│       ├── pom-offline.xml
│       ├── src/                         # application source code
│       └── target/
│           ├── <name>-sut.jar           # runnable system under test
│           └── site/jacoco/             # baseline (T0) coverage report
│
├── LICENSE
└── README.md
```

### Naming conventions

**Sequence → model ordering.** The numeric suffix of each `sequence_NN` folder maps to a fixed permutation:

| Folder | Stage 1 | Stage 2 | Stage 3 | Paper label |
|---|---|---|---|---|
| `sequence_01` | qwen | gpt5mini | glm | Seq1 |
| `sequence_02` | qwen | glm | gpt5mini | Seq2 |
| `sequence_03` | gpt5mini | qwen | glm | Seq3 |
| `sequence_04` | gpt5mini | glm | qwen | Seq4 |
| `sequence_05` | glm | qwen | gpt5mini | Seq5 |
| `sequence_06` | glm | gpt5mini | qwen | Seq6 |

**File prefixes.** Inside an execution folder, the `00_`, `01_` and `02_` prefixes of the per-model metrics files correspond to pipeline **positions 1, 2 and 3**, not to a fixed model. The model name is embedded in the filename, so `sequence_05/.../00_ts01glm_5_2_metrics.json` is glm running *first*, whereas `sequence_01/.../02_ts01glm_5_2_metrics.json` is glm running *last*. This distinction is what the ordering ablation (RQ2) and the position analysis (RQ1, RQ3) exploit.

**Divergency reports.** `*_divergency_report.md` records every assertion the repair agent changed to match the observed implementation behaviour. They exist only for stages where at least one such change occurred, which is why not every execution folder has three of them.

---

## 3. The execution record

`ts01_synthesis_metrics.json` is the single file the analysis reads. One per execution, 270 in total.

```jsonc
{
  "cycle": { ... },                       // identification of the execution
  "steps": [                              // one entry per generation stage, in pipeline order
    {
      "report_order_index": 1,            // pipeline position (1, 2, 3)
      "generation_model": "z-ai/glm-5.2",
      "initial_coverage": 56.02,          // instruction coverage before this stage
      "final_coverage":   80.96,          // instruction coverage after this stage
      "coverage_gain":    24.94,
      "execution_time_seconds": 512.3,
      "llm_totals":  { "total_tokens": ..., "total_requests": ..., "cost_total": ... },
      "efficiency":  { "coverage_gain_per_1k_tokens": ..., "coverage_gain_per_cost_unit": ... }
    }
    // ... two more stages
  ],
  "totals": {
    "initial_coverage_cycle": 56.02,      // T0
    "final_coverage_cycle":   80.96,      // C3
    "coverage_gain_cycle":    24.94,
    "total_tokens": ..., "total_requests": ..., "total_cost": ...
  },
  "rankings": { ... }, "gaps": [ ... ], "warnings": [ ... ]
}
```

All coverage figures are **instruction coverage** as measured by JaCoCo, consistent with the terminology used throughout the paper.

---

## 4. Mapping data to the research questions

Every number in the paper can be traced back to `generated_tests/**/ts01_synthesis_metrics.json` through the scripts in `analysis/`. The table below gives the path for each research question.

### RQ1 — What coverage improvement is achieved, and how is it distributed across pipeline stages?

| Paper artifact | Reproduce with | Reads |
|---|---|---|
| Table 2 (T0 per subject) | `final_analysis.py` | `totals.initial_coverage_cycle` |
| Table 3 (per-subject gain) | `final_analysis.py` | `totals.*_coverage_cycle` |
| Table 4 (stage chain T0→C1→C2→C3) | `final_analysis.py` | `steps[].initial_coverage`, `steps[].final_coverage` |
| Table 5 (gain by position) | `final_analysis.py` | `steps[].coverage_gain`, `steps[].report_order_index` |
| Figures 3–6 (`fig01`–`fig04`) | `refigure.py` | `analysis/executions_repaired.csv` |

The comparison anchor for the multi-model claim is **C1**, the coverage after the first LLM, not T0. In the data this is `steps[0].final_coverage`; the flat equivalent is the `C1` column of `executions_repaired.csv`.

### RQ2 — Does the order in which the models are applied affect final coverage?

| Paper artifact | Reproduce with | Reads |
|---|---|---|
| Table 6 (ranking of the six orderings) | `final_analysis.py` | `totals.final_coverage_cycle` grouped by `sequence_NN` |
| Table 7 (Friedman, Kruskal–Wallis, Holm) | `final_analysis.py` | same, blocked by subject/seed/run |
| Figure 7 (`fig05`) | `refigure.py` | `analysis/executions_repaired.csv` |

The six `sequence_NN` folders **are** the ordering ablation. Comparing `sequence_05` against `sequence_06`, for instance, isolates the effect of swapping positions 2 and 3 while holding position 1 fixed at glm.

### RQ3 — What is the marginal cost–benefit profile, per stage and per model?

| Paper artifact | Reproduce with | Reads |
|---|---|---|
| Table 8 (cost per ordering, per run) | `final_analysis.py` | `totals.total_cost`, `totals.total_tokens` |
| Table 9 (absolute campaign cost) | `final_analysis.py` | sums of `totals.total_cost`, `totals.total_tokens`, `totals.total_requests` per sequence |
| Table 10 (per-model profile) | `final_analysis.py` | `steps[].llm_totals` grouped by `generation_model` |
| Table 11 (model × position) | `final_analysis.py` | `steps[].llm_totals` grouped by model and position |
| Figures 8–10 (`fig06`–`fig08`) | `refigure.py` | `analysis/executions_repaired.csv`, `analysis/steps_repaired.csv` |

All efficiency figures are computed as **ratios of means** (mean gain of a group divided by its mean cost), not as averages of per-execution ratios. The `efficiency` block inside the JSON is a per-stage convenience field and is *not* what the paper reports; averaging it produces a different, unstable ranking when the denominator is small.

Because token counts are recorded per stage, every monetary figure in the paper can be recomputed under any future price schedule without re-running anything.

---

## 5. Reproducing the analysis

```bash
git clone https://github.com/aurimrv/<this-repo>.git
cd <this-repo>
pip install pandas numpy scipy matplotlib

# 1. rebuild the flat datasets from the 270 raw JSON records
python analysis/my_extract.py

# 2. recompute every quantity reported in the paper
python analysis/final_analysis.py

# 3. regenerate the figures of Section 5
python analysis/refigure.py
```

`recompute.py` performs the same computation by an independent route and prints both, which is useful for spotting divergences. `validate.py` extracts the numbers from a compiled PDF of the manuscript and checks each one against the raw data.

---

## 6. Data quality notes

The dataset was verified before analysis: balanced design, exactly three stages per execution, continuous coverage chain (the final coverage of stage *k* equals the initial coverage of stage *k+1*), and per-stage token and cost counters summing to the cycle totals. Two findings are worth stating up front.

**Sixty stages made no model call.** They report zero tokens, zero cost and zero gain. All sixty belong to `spring-actuator-demo` at positions 2 or 3, in runs where the preceding stage had already reached 100% instruction coverage and the stop criterion was met. These are legitimate observations of a model with nothing left to contribute and are **retained** in every analysis; discarding them would bias the per-position and per-model means upwards.

**Two executions had an unattributed increment.** In `sequence_06/run_01/450/features-service` and `sequence_06/run_03/767/features-service`, the cycle total is valid (56.02% → 80.96%) but all three stages report a zero gain, leaving 24.94 pp unassigned; the coverage chain shows the discontinuity between stages 1 and 2. In the other seven runs of the same ordering and subject, stage 1 gains exactly the same 24.94 pp, so the increment was reassigned to position 1. The raw JSON files are **unmodified** — the repair is applied in `final_analysis.py` and is visible there. The paper reports a sensitivity analysis showing that excluding these two executions instead changes no conclusion.

---

## 7. Subject systems

The five systems come from the [Web Fuzzing Dataset](https://github.com/WebFuzzing/Dataset) (formerly EvoMaster Benchmark).

| System | T0 (%) | OpenAPI spec |
|---|---|---|
| `features-service` | 56.02 | `features-service.json` |
| `ncs` | 74.18 | `rest-ncs.json` |
| `restcountries` | 78.27 | `restcountries.json` |
| `scs` | 65.08 | `rest-scs.json` |
| `spring-actuator-demo` | 80.65 | `spring-actuator-demo.json` |

Each folder ships both the original specification and the `_enhanced.json` variant supplied to the pipeline, plus the runnable `*-sut.jar` and the baseline JaCoCo report.

The T0 suites were generated once per system with **EvoMaster 5.0.2**, driven by the OpenAPI document and the running instance, under a one-hour search budget and a fixed seed:

```bash
java -jar evomaster.jar \
  --problemType REST \
  --blackBox true \
  --bbSwaggerUrl file://<openapi-spec>.json \
  --bbTargetUrl http://localhost:8080/<base-path> \
  --maxTime 1h \
  --seed <evomaster-seed> \
  --outputFormat JAVA_JUNIT_4 \
  --testSuiteSplitType NONE \
  --outputFolder <sut>/evomaster/seed<evomaster-seed>
```

Because the same T0 is shared by every ordering, seed and repetition, the baseline cannot confound the comparison among orderings.

---

## 8. Environment

Java 11 · Maven 3.x · EvoMaster 5.0.2 · JaCoCo · JUnit 4 with REST Assured · Python 3 (pandas, numpy, scipy, matplotlib). Models were accessed through the [OpenRouter](https://openrouter.ai) gateway with `temperature = 0.2`, `top_p = 1.0` and a fixed maximum token budget per call; `gpt-5-mini` was fixed as the repair model in every configuration.

---

## 9. License

See [`LICENSE`](LICENSE). The subject systems under `projects/` retain the licences of their original authors.
