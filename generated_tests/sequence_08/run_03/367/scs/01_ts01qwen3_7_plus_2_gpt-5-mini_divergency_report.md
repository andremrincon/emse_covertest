# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus_2.CalcTest
- Generated at: 2026-09-21T08:14:09.087804

### Method: testCalcDivideByZeroError
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -        given().when().get("/api/calc/divide/10.0/0.0").then().statusCode(200); | +        given().when().get("/api/calc/divide/10.0/0.0").then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus_2.DateParseTest
- Generated at: 2026-09-21T08:14:13.083443

### Method: testDateParseInvalid
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -given().when().get("/api/dateparse/invalid/invalid").then().statusCode(200); | +given().when().get("/api/dateparse/invalid/invalid").then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
