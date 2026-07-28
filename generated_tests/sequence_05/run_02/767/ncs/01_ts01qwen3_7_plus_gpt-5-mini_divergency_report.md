# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.FisherTest
- Generated at: 2026-07-11T19:09:10.742624

### Method: testFisherInvalidNParameter
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -        given().when().get("/api/fisher/10/-3/0.75").then().statusCode(200); | +        given().when().get("/api/fisher/10/-3/0.75").then().statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
