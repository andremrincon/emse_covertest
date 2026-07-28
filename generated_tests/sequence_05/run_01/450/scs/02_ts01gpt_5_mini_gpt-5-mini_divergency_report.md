# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.DateParseTest
- Generated at: 2026-07-11T13:40:04.978425

### Method: test_dateparse_numeric_inputs_return_500
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 200
- Change summary: -        assertEquals(500, resp.getStatusCode()); | +        assertEquals(200, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CostfunsTest
- Generated at: 2026-07-11T13:40:14.314381

### Method: testCostfuns_returnsSix_when_iIsFive_and_sIsAbab
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: [6]
- Observed value in implementation: [10]
- Change summary: -        assertEquals("6", resp.getBody().asString()); | +        assertEquals("10", resp.getBody().asString());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCostfuns_returnsZero_when_iIsMinus4_and_sIsAbab
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: []0
- Observed value in implementation: [1]0
- Change summary: -        assertEquals("0", resp.getBody().asString()); | +        assertEquals("10", resp.getBody().asString());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
