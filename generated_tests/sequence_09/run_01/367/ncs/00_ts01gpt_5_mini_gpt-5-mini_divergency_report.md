# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.RemainderTest
- Generated at: 2026-09-21T15:04:33.421312

### Method: testBEqualsZeroReturnsBadRequest
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -        given().when().get("/api/remainder/{a}/{b}", 17, 0).then().statusCode(200); | +        given().when().get("/api/remainder/{a}/{b}", 17, 0).then().statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testAEqualsZeroReturnsBadRequest
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -        given().when().get("/api/remainder/{a}/{b}", 0, 5).then().statusCode(200); | +        given().when().get("/api/remainder/{a}/{b}", 0, 5).then().statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
