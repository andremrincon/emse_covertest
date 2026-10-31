# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini_3.CalcTest
- Generated at: 2026-09-21T22:33:36.965556

### Method: testDivideByZeroReturns500
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -        given().when().get("/api/calc/{op}/{arg1}/{arg2}", op, arg1, arg2).then().statusCode(200); | +        given().when().get("/api/calc/{op}/{arg1}/{arg2}", op, arg1, arg2).then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini_3.DateParseTest
- Generated at: 2026-09-21T22:33:40.880971

### Method: test_Superday_Movember_returns_500
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -        act.then().statusCode(200); | +        act.then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
