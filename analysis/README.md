# EMSE analysis package — 405 executions (multi-model + single-model control)

## Configurations
| Folder | Tag | Stage 1 → 2 → 3 | Arm |
|---|---|---|---|
| sequence_01 | Seq1 | qwen → gpt5mini → glm | multi |
| sequence_02 | Seq2 | qwen → glm → gpt5mini | multi |
| sequence_03 | Seq3 | gpt5mini → qwen → glm | multi |
| sequence_04 | Seq4 | gpt5mini → glm → qwen | multi |
| sequence_05 | Seq5 | glm → qwen → gpt5mini | multi |
| sequence_06 | Seq6 | glm → gpt5mini → qwen | multi |
| sequence_07 | Seq7 | glm × 3 | single |
| sequence_08 | Seq8 | qwen × 3 | single |
| sequence_09 | Seq9 | gpt5mini × 3 | single |

Each configuration: 5 systems × 3 seeds (367, 450, 767) × 3 runs = 45 executions.

## Pipeline
```bash
pip install pandas numpy scipy matplotlib
python scripts/01_extract_data.py        # raw ts01_synthesis_metrics.json -> data/*.csv (needs raw tree)
python scripts/02_analysis.py            # tables/*.csv + results/numbers.json
python scripts/03_visualize.py           # figures/ (file names = manuscript names)
python scripts/04_validate_paper.py <compiled.pdf>   # checks every quoted number
```
`common.py` holds loading, arm tagging, and the data repair shared by all scripts.

## Methodological decisions
- **Unit of analysis.** Executions are not independent. Configurations are compared
  paired within blocks (system × seed × run, n = 45); conclusions are also checked on
  system × configuration cells.
- **Efficiency** = ratio of means (mean gain / mean cost), never mean of ratios.
- **RQ2 equivalence.** TOST via 90% bootstrap CIs of paired differences, margins 1 and 2 pp,
  plus a Bonferroni-adjusted variant over the 15 pairs.
- **RQ4 control.** Each multi-model pair is matched with the single-model configuration that
  shares its FIRST model (glm: Seq5,6 vs Seq7; qwen: Seq1,2 vs Seq8; gpt5mini: Seq3,4 vs Seq9),
  so the contrast isolates positions 2–3. Mean differences are tested with a paired sign-flip
  permutation test (50,000 resamples), Wilcoxon reported alongside, Holm over the three models.
- **Stall** = a stage adding < 1 pp. Rescue = positions 2–3 adding ≥ 1 pp after a stalled stage 1.
- Bootstrap: 10,000 resamples, fixed seed; CIs may differ from earlier drafts in the 2nd decimal.

## Data quality
- 91 of 1,215 stages (60 multi, 31 single) made no request: all `spring-actuator-demo`,
  positions 2–3, preceding stage already at 100%. Legitimate observations, retained.
- `sequence_06/run_01/450` and `run_03/767` on `features-service`: valid cycle total
  (56.02 → 80.96) but zero gain at all stages. Increment reassigned to position 1 (seven sibling
  runs gain exactly 24.94 pp there). Raw CSVs untouched; repair in `common.py`; `02_analysis.py`
  reports the sensitivity analysis excluding both records.

## Pipeline failures on features-service (kept in the data)
Six executions (sequence_05 run_01/367, run_02/367, run_02/767; sequence_07 run_01/767,
run_02/367, run_02/450) failed because of a COVERTEST defect: one glm-generated class per run
imported the legacy package `com.jayway.restassured`; the Compilation Gate sent the global
compiler log to every class's repair (the culprit's code reached the repair model only once),
the stage aborted, and its untreated classes then blocked the global execution gate of the
later stages (same residual failure count in stages 2 and 3). They are NOT removed;
`common.py` flags them (`pipeline_failure`) and `02_analysis.py` reports a sensitivity analysis
(`pf_*` keys in results/numbers.json).

## Figure mapping (analysis → manuscript)
All nine configurations: fig03 evolution, fig04 initial vs final, fig06 heatmap, fig07 boxplot,
fig09 gain per dollar. Split multi (a) / single (b), same scales: fig05 gain by position,
fig08 cost vs coverage, fig10 efficiency by model.

The earlier `analysis.R` was dropped: it applied listwise deletion (removing the zero-cost
stages) and mean-of-ratios efficiencies. The Python scripts are the single source of truth.
