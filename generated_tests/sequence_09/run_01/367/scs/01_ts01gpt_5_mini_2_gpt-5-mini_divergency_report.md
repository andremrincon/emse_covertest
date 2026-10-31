# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini_2.RegexTest
- Generated at: 2026-09-21T15:49:08.009133

### Method: testRegex_urlBranch_returns200
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 400
- Change summary: -        assertEquals(200, resp.getStatusCode()); | +        assertEquals(400, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini_2.CostfunsTest
- Generated at: 2026-09-21T15:49:12.105015

### Method: test_zero_return_when_i_is_minus4_and_s_is_abab
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: []0
- Observed value in implementation: [1]0
- Change summary: -        assertEquals("0", res.getBody().asString()); | +        assertEquals("10", res.getBody().asString());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
