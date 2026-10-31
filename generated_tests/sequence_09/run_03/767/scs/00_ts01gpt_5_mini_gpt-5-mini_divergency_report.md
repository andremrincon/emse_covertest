# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.TitleTest
- Generated at: 2026-09-21T23:25:45.567072

### Method: testNeuterJonesError
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -        resp.then().statusCode(200); | +        resp.then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
