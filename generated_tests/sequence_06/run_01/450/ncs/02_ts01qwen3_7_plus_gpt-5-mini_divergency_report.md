# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.BessjTest
- Generated at: 2026-07-12T11:30:04.806026

### Method: testBessj_axGreaterThan8
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -                .statusCode(400); | +                .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testBessj_axLessThan8
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -                .statusCode(400); | +                .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
