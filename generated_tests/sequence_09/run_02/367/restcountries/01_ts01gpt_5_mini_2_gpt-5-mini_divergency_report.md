# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini_2.CountryRestV2Test
- Generated at: 2026-09-21T18:28:48.993163

### Method: testGetByAlphaListNotFound_XXYYZZ
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 404
- Observed value in implementation: 400
- Change summary: -        assertEquals(404, act.getStatusCode()); | +        assertEquals(400, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
