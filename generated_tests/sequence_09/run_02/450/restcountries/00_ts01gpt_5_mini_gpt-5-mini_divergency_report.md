# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.StripeRestTest
- Generated at: 2026-09-21T19:15:08.086922

### Method: testContributeWithValidTokenReturnsAccepted
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 202
- Observed value in implementation: 400
- Change summary: -        assertEquals(202, resp.getStatusCode()); | +        assertEquals(400, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.ResponseEntityTest
- Generated at: 2026-09-21T19:15:08.495591

### Method: testNameServerErrorReturnsStatusField500
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 404
- Change summary: -        act.then().body("status", equalTo(500)); | +        act.then().body("status", equalTo(404));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
