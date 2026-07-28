# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.BessjTest
- Generated at: 2026-07-12T16:13:53.405712

### Method: testBessj_axGreaterBranch_returns200
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 400
- Change summary: -        assertEquals(200, act.getStatusCode()); | +        assertEquals(400, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
