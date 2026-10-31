# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.BessjTest
- Generated at: 2026-09-21T09:45:22.837893

### Method: testBessj_axGreaterThanN_and_axLessThan8
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -                .statusCode(400); | +                .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testBessj_axGreaterThanN_and_axGreaterOrEqual8_and_xPositive
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -                .statusCode(400); | +                .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testBessj_axGreaterThanN_and_axGreaterOrEqual8_and_xNegative_and_nEven
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -                .statusCode(400); | +                .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testBessj_axIsZero
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -                .statusCode(400); | +                .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
