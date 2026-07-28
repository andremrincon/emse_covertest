# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.CalcTest
- Generated at: 2026-07-11T22:26:37.076455

### Method: testCalcDivisionByZero
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -            .statusCode(200); | +            .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.RegexTest
- Generated at: 2026-07-11T22:26:39.022185

### Method: testSubjectMatchesUrl
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -            .statusCode(404); | +            .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.TitleTest
- Generated at: 2026-07-11T22:26:43.243815

### Method: testNeuterSmith
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -            .statusCode(200); | +            .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
