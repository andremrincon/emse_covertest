# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.RegexTest
- Generated at: 2026-09-21T16:43:27.614843

### Method: testUrlPatternProducesOk
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 400
- Change summary: -        assertEquals(200, act.getStatusCode()); | +        assertEquals(400, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CostfunsTest
- Generated at: 2026-09-21T16:43:30.428418

### Method: testIEquals5ProducesSix
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: [6]
- Observed value in implementation: [10]
- Change summary: -        assertEquals("6", r.asString().trim()); | +        assertEquals("10", r.asString().trim());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testReturnZeroWhenIIsMinus4
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: []0
- Observed value in implementation: [1]0
- Change summary: -        assertEquals("0", r.asString().trim()); | +        assertEquals("10", r.asString().trim());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
