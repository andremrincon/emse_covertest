# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.RemainderTest
- Generated at: 2026-07-11T14:07:10.372747

### Method: testBEqualsZero_returnsBadRequest
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -        resp.then().statusCode(200); | +        resp.then().statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
