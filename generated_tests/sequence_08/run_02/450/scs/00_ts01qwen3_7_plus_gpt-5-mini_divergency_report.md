# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.CalcTest
- Generated at: 2026-09-21T05:40:31.762825

### Method: testDivideByZero
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -given().when().get("http://localhost:8080/api/calc/divide/10/0").then().statusCode(200); | +given().when().get("http://localhost:8080/api/calc/divide/10/0").then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
