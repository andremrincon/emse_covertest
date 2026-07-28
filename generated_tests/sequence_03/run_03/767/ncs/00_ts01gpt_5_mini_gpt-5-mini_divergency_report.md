# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.NcsRestTest
- Generated at: 2026-07-09T22:29:34.219376

### Method: testFisher_Returns400_ForInvalidX
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -        given().when().get("/api/fisher/{m}/{n}/{x}", 8, 5, 1.2).then().statusCode(200); | +        given().when().get("/api/fisher/{m}/{n}/{x}", 8, 5, 1.2).then().statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
