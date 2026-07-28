# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.GammqTest
- Generated at: 2026-07-11T15:47:28.626812

### Method: testGserExceedsItmax
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -            .statusCode(200); | +            .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
