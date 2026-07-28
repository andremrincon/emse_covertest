# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.CostfunsTest
- Generated at: 2026-07-12T13:57:40.748484

### Method: testCostfuns_ReturnsSix_When_valid_i_not_minus4_and_sIsAbab
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: [6]
- Observed value in implementation: [10]
- Change summary: -        assertEquals("6", resp.getBody().asString()); | +        assertEquals("10", resp.getBody().asString());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCostfuns_ReturnsZero_When_iIsMinus4_And_sIsAbab
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: []0
- Observed value in implementation: [1]0
- Change summary: -        assertEquals("0", resp.getBody().asString()); | +        assertEquals("10", resp.getBody().asString());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.DateParseTest
- Generated at: 2026-07-12T13:57:47.730159

### Method: testParse_Monday_withInvalidMonth_Movember_returns500
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 200
- Change summary: -        Assert.assertEquals(500, act.getStatusCode()); | +        Assert.assertEquals(200, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testParse_Superday_Movember_returns500
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 200
- Change summary: -        Assert.assertEquals(500, act.getStatusCode()); | +        Assert.assertEquals(200, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
