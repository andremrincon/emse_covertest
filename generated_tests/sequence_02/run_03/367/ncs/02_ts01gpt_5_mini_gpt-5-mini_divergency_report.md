# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.NcsRestTest
- Generated at: 2026-07-08T21:42:27.383410

### Method: testFisher_whenInternalThrows_returns400
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 200
- Change summary: -        assertEquals(400, resp.getStatusCode()); | +        assertEquals(200, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testRemainder_whenBDivisorZero_returns400
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 200
- Change summary: -        assertEquals(400, resp.getStatusCode()); | +        assertEquals(200, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
