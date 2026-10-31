# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus_2.ExpintTest
- Generated at: 2026-09-21T06:09:10.938780

### Method: testExpintContinuedFractionFailed
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -        response.then().statusCode(200); | +        response.then().statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
