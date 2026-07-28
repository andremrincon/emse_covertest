# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.RemainderTest
- Generated at: 2026-07-12T01:12:06.299416

### Method: testRemainder_AIsZero_ReturnsBadRequest
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -        res.then().statusCode(200); | +        res.then().statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
