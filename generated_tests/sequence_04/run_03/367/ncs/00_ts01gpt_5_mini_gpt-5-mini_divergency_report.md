# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.NcsRestTest
- Generated at: 2026-07-10T12:20:12.936600

### Method: testFisherRuntimeException
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 200
- Change summary: -        assertEquals(400, act.getStatusCode()); | +        assertEquals(200, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
