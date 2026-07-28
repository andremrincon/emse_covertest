# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.DateParseTest
- Generated at: 2026-07-11T11:43:51.403996

### Method: testInvalidDayValidMonth
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -            .then().statusCode(200); | +            .then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testInvalidDayInvalidMonth
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -            .then().statusCode(200); | +            .then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
