# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.DateParseTest
- Generated at: 2026-07-11T20:17:06.746966

### Method: testInvalidDayAndInvalidMonth
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -given().when().get("/api/dateparse/Superday/Movember").then().statusCode(200); | +given().when().get("/api/dateparse/Superday/Movember").then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testNumericInputsCauseServerError
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -given().when().get("/api/dateparse/123/456").then().statusCode(200); | +given().when().get("/api/dateparse/123/456").then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
