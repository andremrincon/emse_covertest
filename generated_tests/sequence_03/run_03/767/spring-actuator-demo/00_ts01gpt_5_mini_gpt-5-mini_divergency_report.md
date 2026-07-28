# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.SampleControllerTest
- Generated at: 2026-07-09T23:51:48.905906

### Method: testSlowApiWithNegativeDelayReturns500
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -        given().queryParam("delay", -1).when().get("/slowApi").then().statusCode(200); | +        given().queryParam("delay", -1).when().get("/slowApi").then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
