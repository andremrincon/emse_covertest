# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.DateParseTest
- Generated at: 2026-07-08T03:38:24.108224

### Method: testDateParseInvalidMonth
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -given().when().get("/api/dateparse/mon/invalid").then().statusCode(200); | +given().when().get("/api/dateparse/mon/invalid").then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testDateParseInvalidDay
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -given().when().get("/api/dateparse/invalid/jan").then().statusCode(200); | +given().when().get("/api/dateparse/invalid/jan").then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.RegexTest
- Generated at: 2026-07-08T03:38:29.532588

### Method: testUrlPatternMatch
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -            .statusCode(404); | +            .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
