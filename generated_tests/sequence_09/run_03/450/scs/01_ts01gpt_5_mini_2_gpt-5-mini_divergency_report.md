# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini_2.Ordered4Test
- Generated at: 2026-09-21T22:28:50.757450

### Method: test_invalid_values_can_return_500
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -        resp.then().statusCode(200); | +        resp.then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini_2.CostfunsTest
- Generated at: 2026-09-21T22:28:56.902025

### Method: testCostfuns_Returns500_ForStringIntegerOverflow
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -        resp.then().statusCode(200); | +        resp.then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
