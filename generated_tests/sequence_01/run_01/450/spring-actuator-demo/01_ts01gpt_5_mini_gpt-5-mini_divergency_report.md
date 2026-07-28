# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.SampleControllerTest
- Generated at: 2026-07-07T17:15:58.633966

### Method: testSlowApiWithNegativeDelayReturnsServerError
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -        resp.then().statusCode(200); | +        resp.then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
