# Replication Package - An Incremental Multi-Model LLM Strategy for Automated REST API Integration Testing Guided by Uncovered Code

This repository holds the raw data, the generated test suites, the subject systems, and the analysis scripts of the empirical study reported in:

> A. M. Rincon and A. M. R. Vincenzi. *An Incremental Multi-Model LLM Strategy for Automated REST API Integration Testing Guided by Uncovered Code.* Submitted to **Empirical Software Engineering** (Special Issue).

The tool that produced these data, **COVERTEST**, lives in a separate repository:

**➡ [github.com/aurimrv/COVERTEST](https://github.com/aurimrv/COVERTEST)**

Everything below is data and analysis. If you want to *run* the pipeline on your own subject, go to the tool repository instead.

---

## 1. What is in here

The study evaluates an incremental, coverage-guided pipeline that starts from an EvoMaster baseline suite (T0) and applies three LLM generation stages, each targeting the instructions still uncovered by the previous stage. The experiment has two arms.

**Multi-model arm** — all six orderings of three different models:

| Factor | Levels | Values |
|---|---|---|
| Model ordering | 6 | all permutations of `qwen3.7-plus`, `gpt-5-mini`, `glm-5.2` |
| Subject system | 5 | `features-service`, `ncs`, `restcountries`, `scs`, `spring-actuator-demo` |
| Random seed | 3 | 367, 450, 767 |
| Repetition | 3 | `run_01`, `run_02`, `run_03` |

6 × 5 × 3 × 3 = **270 executions**.

**Single-model control** — the same model at all three positions, added to test whether model diversity, rather than simply running three stages, is what produces the gain:

| Factor | Levels | Values |
|---|---|---|
| Configuration | 3 | `glm`×3 (Seq7), `qwen`×3 (Seq8), `gpt-5-mini`×3 (Seq9) |
| Subject system | 5 | the same five |
| Random seed | 3 | 367, 450, 767 |
| Repetition | 3 | `run_01`, `run_02`, `run_03` |

3 × 5 × 3 × 3 = **135 executions**.

Together, **270 + 135 = 405 executions**, each comprising three generation stages, for **1,215 stages** in total. Everything other than the generation models is held fixed across both arms: the same T0 suites, the same repair model (`gpt-5-mini`), the same prompts, and the same stopping criteria. Every execution is preserved in full: generated sources, compiled classes, JaCoCo reports, Surefire reports, LLM traces and metrics.

**Campaign totals**

| Arm | Executions | Cost (US$) | Tokens | API requests | Pipeline time (h) |
|---|---:|---:|---:|---:|---:|
| Multi-model (Seq1–Seq6) | 270 | 131.16 | 128,231,475 | 10,740 | 92.3 |
| Single-model control (Seq7–Seq9) | 135 | 77.37 | 73,577,331 | 5,618 | 27.2 |
| **Total** | **405** | **208.53** | **201,808,806** | **16,358** | **119.5** |

Figures exclude the one-off EvoMaster baseline generation. Per-sequence totals are in `analysis/tables/rq3_by_sequence.csv`.

---

## 2. Repository structure

```
emse_covertest/
├── analysis/                      # scripts + curated datasets (Section 5 of the paper)
│   ├── scripts/
│   │   ├── 01_extract_data.py     # raw JSON  ->  flat CSV (data/)
│   │   ├── common.py              # shared loading, arm tagging, data repair, flags
│   │   ├── 02_analysis.py         # all statistics (RQ1-RQ4) -> tables/, results/
│   │   ├── 03_visualize.py        # all figures of Section 5 -> figures/
│   │   └── 04_validate_paper.py   # cross-checks a compiled PDF against the raw data
│   ├── data/
│   │   ├── executions.csv         # 405 rows, one per execution
│   │   ├── steps.csv              # 1,215 rows, one per generation stage
│   │   └── data_quality_flags.csv # records whose step gains do not sum to the cycle total
│   ├── tables/                    # every CSV behind a table of the paper
│   ├── figures/                   # fig03 .. fig10b, in PDF and PNG
│   └── results/numbers.json       # the reported quantities, machine-readable
│
├── generated_tests/               # RAW DATA: one folder per execution
│   ├── model_sequence             # ordering of the generation models per sequence
│   └── sequence_01 .. sequence_09/
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
├── projects/                      # the five subject systems (Web Fuzzing Dataset)
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

**Sequence → model ordering.** The numeric suffix of each `sequence_NN` folder maps to a fixed configuration. Sequences 01–06 are the multi-model arm, 07–09 the single-model control. The same mapping is in `generated_tests/model_sequence`.

| Folder | Stage 1 | Stage 2 | Stage 3 | Paper label | Arm |
|---|---|---|---|---|---|
| `sequence_01` | qwen | gpt5mini | glm | Seq1 | multi-model |
| `sequence_02` | qwen | glm | gpt5mini | Seq2 | multi-model |
| `sequence_03` | gpt5mini | qwen | glm | Seq3 | multi-model |
| `sequence_04` | gpt5mini | glm | qwen | Seq4 | multi-model |
| `sequence_05` | glm | qwen | gpt5mini | Seq5 | multi-model |
| `sequence_06` | glm | gpt5mini | qwen | Seq6 | multi-model |
| `sequence_07` | glm | glm | glm | Seq7 | single-model control |
| `sequence_08` | qwen | qwen | qwen | Seq8 | single-model control |
| `sequence_09` | gpt5mini | gpt5mini | gpt5mini | Seq9 | single-model control |

**File prefixes.** Inside an execution folder, the `00_`, `01_` and `02_` prefixes of the per-model metrics files correspond to pipeline **positions 1, 2 and 3**, not to a fixed model. The model name is embedded in the filename, so `sequence_05/.../00_ts01glm_5_2_metrics.json` is glm running *first*, whereas `sequence_01/.../02_ts01glm_5_2_metrics.json` is glm running *last*. This distinction is what the ordering ablation (RQ2), the position analysis (RQ1, RQ3) and the matched multi-vs-single comparison (RQ4) exploit. In the control sequences the three files carry the same model name and differ only in prefix.

**Divergency reports.** `*_divergency_report.md` records every assertion the repair agent changed to match the observed implementation behaviour. They exist only for stages where at least one such change occurred, which is why not every execution folder has three of them.

---

## 3. The execution record

`ts01_synthesis_metrics.json` is the single file the analysis reads. One per execution, 405 in total.

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

Every number in the paper can be traced back to `generated_tests/**/ts01_synthesis_metrics.json` through the scripts in `analysis/scripts/`. `02_analysis.py` produces every statistic and writes the CSVs listed below; `03_visualize.py` produces every figure. Table and figure numbers are those of the submitted manuscript.

### RQ1 — What coverage improvement is achieved, and how is it distributed across pipeline stages?

| Paper artifact | CSV / figure | Reads |
|---|---|---|
| Table 2 (T0 per subject) | `tables/rq1_by_project.csv` | `totals.initial_coverage_cycle` |
| Table 3 (per-subject gain) | `tables/rq1_by_project.csv` | `totals.*_coverage_cycle` |
| Table 4 (stage chain T0→C1→C2→C3) | `tables/rq1_stagewise.csv` | `steps[].initial_coverage`, `steps[].final_coverage` |
| Table 5 (gain by position) | `tables/rq1_by_position.csv` | `steps[].coverage_gain`, `steps[].report_order_index` |
| Figure 3 (coverage evolution) | `figures/fig03_coverage_evolution` | `data/executions.csv` |
| Figure 4 (initial vs final) | `figures/fig04_initial_vs_final` | `data/executions.csv` |
| Figure 5 (gain by position, multi-model) | `figures/fig05a_gain_by_position_multi` | `data/steps.csv` |
| Figure 6 (final coverage by subject) | `figures/fig06_final_cov_by_project` | `data/executions.csv` |

The comparison anchor for the multi-model claim is **C1**, the coverage after the first LLM, not T0. In the data this is `steps[0].final_coverage`; the flat equivalent is the `C1` column added by `common.py`. The increment beyond the first stage, broken down by leading model, is in `tables/rq1_increment_by_leading_model.csv`.

### RQ2 — Does the order in which the models are applied affect final coverage?

| Paper artifact | CSV / figure | Reads |
|---|---|---|
| Table 6 (ranking of the six orderings) | `tables/rq3_by_sequence.csv` | `totals.final_coverage_cycle` grouped by `sequence_NN` |
| Table 7 (Friedman, Kruskal–Wallis, Holm) | `tables/rq2_friedman_by_project.csv` | same, blocked by subject/seed/run |
| Table 8 (TOST equivalence of the orderings) | `tables/rq2_pairwise_equivalence.csv` | same, paired within blocks |
| Figure 7 (final-coverage distribution) | `figures/fig07_sequence_boxplot` | `data/executions.csv` |

The six `sequence_01`–`sequence_06` folders **are** the ordering ablation. Comparing `sequence_05` against `sequence_06`, for instance, isolates the effect of swapping positions 2 and 3 while holding position 1 fixed at glm. The equivalence analysis reports, for every pair, the smallest margin at which equivalence holds, so no single margin has to be assumed; `tables/rq2_features_service_stalls.csv` isolates the system-specific anomaly discussed in Section 5.2.

### RQ3 — What is the marginal cost–benefit profile, per stage and per model?

| Paper artifact | CSV / figure | Reads |
|---|---|---|
| Table 9 (cost per configuration, per run) | `tables/rq3_by_sequence.csv` | `totals.total_cost`, `totals.total_tokens` |
| Table 10 (absolute campaign cost) | `tables/rq3_by_sequence.csv` (`*_total` columns) | sums per sequence |
| Table 11 (per-model profile) | `tables/rq3_by_model_and_arm.csv` | `steps[].llm_totals` grouped by `generation_model` |
| Table 12 (model × position) | `tables/rq3_by_model_position_and_arm.csv` | `steps[].llm_totals` grouped by model and position |
| Figure 8 (cost vs coverage, multi-model) | `figures/fig08a_cost_vs_coverage_multi` | `data/executions.csv` |
| Figure 9 (coverage gain per dollar) | `figures/fig09_cost_benefit_ranking` | `data/executions.csv` |
| Figure 10 (per-model efficiency, multi-model) | `figures/fig10a_efficiency_by_model_multi` | `data/steps.csv` |

All efficiency figures are computed as **ratios of means** (mean gain of a group divided by its mean cost), not as averages of per-execution ratios. The `efficiency` block inside the JSON is a per-stage convenience field and is *not* what the paper reports; averaging it produces a different, unstable ranking when the denominator is small.

Because token counts are recorded per stage, every monetary figure in the paper can be recomputed under any future price schedule without re-running anything.

### RQ4 — Is applying three different models better than applying one model three times?

| Paper artifact | CSV / figure | Reads |
|---|---|---|
| Table 13 (control, matched on the first model) | `tables/rq4_matched_first_model.csv` | `totals.final_coverage_cycle`, both arms |
| Figure 11 (gain by position, control) | `figures/fig05b_gain_by_position_single` | `data/steps.csv` |
| Figure 12 (cost vs coverage, control) | `figures/fig08b_cost_vs_coverage_single` | `data/executions.csv` |
| Figure 13 (per-model efficiency, control) | `figures/fig10b_efficiency_by_model_single` | `data/steps.csv` |
| Best single vs each ordering | `tables/rq4_best_single_vs_multi.csv` | both arms |
| Per-subject contrast | `tables/rq4_by_project.csv` | both arms |
| Stall-rescue detail | `tables/rq4_stall_rescue_detail.csv` | `data/steps.csv` |

The comparison is **matched on the first model**: Seq7 (`glm`×3) is contrasted with the two orderings that also lead with glm, Seq8 (`qwen`×3) with the two that lead with qwen, and Seq9 (`gpt-5-mini`×3) with the two that lead with gpt-5-mini. Matching this way holds the dominant factor — which model runs first — constant, so the contrast isolates what diversity at positions 2 and 3 contributes. Comparing a control sequence against the pooled multi-model mean would confound the two.

Because `sequence_01`–`sequence_06` and `sequence_07`–`sequence_09` share the same T0 suites, systems, seeds and runs, the two arms are paired block by block (`project` × `seed` × `run`). `common.py` builds that pairing; `tables/design_check.csv` confirms all nine sequences have exactly 45 executions.

---

## 5. Reproducing the analysis

```bash
git clone https://github.com/andremrincon/emse_covertest.git
cd emse_covertest
pip install pandas numpy scipy matplotlib

# 1. rebuild the flat datasets from the 405 raw JSON records
python analysis/scripts/01_extract_data.py

# 2. recompute every quantity reported in the paper
python analysis/scripts/02_analysis.py

# 3. regenerate the figures of Section 5
python analysis/scripts/03_visualize.py

# 4. (optional) check a compiled PDF of the manuscript against the raw data
python analysis/scripts/04_validate_paper.py path/to/2025-emse-api-incremental.pdf
```

The scripts resolve their paths relative to their own location, so they can be run from anywhere. Steps 2 and 3 read only `analysis/data/`, which is committed, so the full `generated_tests/` tree is needed only for step 1.

`04_validate_paper.py` re-derives each reported quantity from the committed datasets and checks that the value appears in the text of the compiled PDF. On the submitted version it reports `CHECKS 71 PASS 71 FAIL 0` and `PIPELINE-FAILURE CHECKS 9 PASS 9 FAIL 0`.

---

## 6. Data quality notes

The dataset was verified before analysis: balanced design (45 executions per sequence), exactly three stages per execution, continuous coverage chain (the final coverage of stage *k* equals the initial coverage of stage *k+1*), and per-stage token and cost counters summing to the cycle totals. Three findings are worth stating up front. All three are **retained** in every analysis, because they are genuine outcomes of the pipeline as evaluated; the paper reports how much each one weighs.

**Ninety-one stages made no model call.** They report zero tokens, zero cost and zero gain — 60 in the multi-model arm and 31 in the control. All of them belong to `spring-actuator-demo` at positions 2 or 3, in runs where the preceding stage had already reached 100% instruction coverage and the stop criterion was met. These are legitimate observations of a model with nothing left to contribute; discarding them would bias the per-position and per-model means upwards.

**Two executions had an unattributed increment.** In `sequence_06/run_01/450/features-service` and `sequence_06/run_03/767/features-service`, the cycle total is valid (56.02% → 80.96%) but all three stages report a zero gain, leaving 24.94 pp unassigned; the coverage chain shows the discontinuity between stages 1 and 2. In the other seven runs of the same ordering and subject, stage 1 gains exactly the same 24.94 pp, so the increment was reassigned to position 1. The raw JSON files are **unmodified** — the repair is applied in `analysis/scripts/common.py` and documented there, and the two records are listed in `analysis/data/data_quality_flags.csv`. The paper reports a sensitivity analysis showing that excluding these two executions instead changes no conclusion.

**Six executions stalled on `features-service`.** Three of Seq5 and three of Seq7 — 6 of the 405 executions, 1.5% — end with the final coverage still at the T0 value or barely above it. All six lead with `glm` at position 1, and in each one `glm` generated a single test class (of 16) importing a legacy REST Assured package that does not exist in the configured version. The Compilation Gate then forwarded the whole global compiler log to the parallel repair of all 16 classes, so most repair prompts paired the error of one file with the code of another and could not converge; the stage stopped with a global compilation failure before measuring anything, and the untreated classes went on to block the execution gate of stages 2 and 3. The generation error itself is stochastic — `gpt-5-mini` repaired the same import in other classes once it received the matching code — whereas the failure to contain it is a deterministic property of how the gate batched the log. These six are flagged in `analysis/scripts/common.py` (`PIPELINE_FAILURES`), counted in `analysis/tables/rq2_features_service_stalls.csv`, and the paper reports every affected quantity with and without them.

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

Because the same T0 is shared by every configuration, seed and repetition — in both arms — the baseline cannot confound either the comparison among orderings or the comparison between the multi-model arm and the control.

---

## 8. Environment

Java 11 · Maven 3.x · EvoMaster 5.0.2 · JaCoCo · JUnit 4 with REST Assured · Python 3 (pandas, numpy, scipy, matplotlib). Models were accessed through the [OpenRouter](https://openrouter.ai) gateway with `temperature = 0.2`, `top_p = 1.0` and a fixed maximum token budget per call; `gpt-5-mini` was fixed as the repair model in every configuration, in both arms. The seed was broadcast to all models of a sequence (`LLM_SEED_GEN` = `LLM_SEED_FIX` = 367, 450 or 767); because providers do not guarantee bitwise determinism at a fixed seed, the design repeats every configuration three times and the paper reports variance rather than relying on seeds alone.

---

## 9. License

See [`LICENSE`](LICENSE). The subject systems under `projects/` retain the licences of their original authors.
