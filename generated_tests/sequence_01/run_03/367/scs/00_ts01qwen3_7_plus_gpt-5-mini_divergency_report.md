# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.PatTest
- Generated at: 2026-07-08T01:51:00.651206

### Method: testServerError500
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -            .statusCode(200); | +            .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
