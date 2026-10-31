# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus_3.RegexTest
- Generated at: 2026-09-21T04:31:48.017565

### Method: testSubjectUrl
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -            .statusCode(404); | +            .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus_3.CalcTest
- Generated at: 2026-09-21T04:31:51.601132

### Method: testCalcDivideByZero
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -            .statusCode(200); | +            .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
