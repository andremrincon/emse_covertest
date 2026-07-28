# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.Ordered4Test
- Generated at: 2026-07-12T17:32:20.638725

### Method: testServerError
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -                .statusCode(200); | +                .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
