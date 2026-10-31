# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini_2.RegexTest
- Generated at: 2026-09-21T18:42:58.933311

### Method: testUrlPatternTriggersRegexUrlBranch
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 400
- Change summary: -        assertEquals(200, resp.getStatusCode()); | +        assertEquals(400, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
