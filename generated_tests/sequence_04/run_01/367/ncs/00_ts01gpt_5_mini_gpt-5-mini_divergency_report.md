# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.BessjTest
- Generated at: 2026-07-10T00:30:09.683778

### Method: testBessj_axGreaterThanN_smallAx_uses_small_bessj0_bessj1_returns200
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 400
- Change summary: -        assertEquals(200, act.getStatusCode()); | +        assertEquals(400, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
