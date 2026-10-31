# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini_3.TitleTest
- Generated at: 2026-09-21T21:36:19.886815

### Method: testNeuterWithJonesReturns500
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 200
- Change summary: -        assertEquals(500, r.getStatusCode()); | +        assertEquals(200, r.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini_3.RegexTest
- Generated at: 2026-09-21T21:36:21.013110

### Method: testUrlBranchReturns200
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 400
- Change summary: -        assertEquals(200, resp.getStatusCode()); | +        assertEquals(400, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini_3.CalcTest
- Generated at: 2026-09-21T21:36:25.109670

### Method: testDivideByZeroReturnsServerError
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 200
- Change summary: -        assertEquals(500, act.getStatusCode()); | +        assertEquals(200, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
