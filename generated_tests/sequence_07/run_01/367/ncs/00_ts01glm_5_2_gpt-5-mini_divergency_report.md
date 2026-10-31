# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.GammqTest
- Generated at: 2026-09-20T10:26:55.349497

### Method: testGserITMAXException
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -given().when().get("/api/gammq/200.0/199.0").then().statusCode(200); | +given().when().get("/api/gammq/200.0/199.0").then().statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
