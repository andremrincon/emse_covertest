# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.RemainderTest
- Generated at: 2026-07-08T09:48:33.058627

### Method: testRemainderAZero
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -given().when().get("/api/remainder/0/5").then().statusCode(200); | +given().when().get("/api/remainder/0/5").then().statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testRemainderBZero
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -given().when().get("/api/remainder/5/0").then().statusCode(200); | +given().when().get("/api/remainder/5/0").then().statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
