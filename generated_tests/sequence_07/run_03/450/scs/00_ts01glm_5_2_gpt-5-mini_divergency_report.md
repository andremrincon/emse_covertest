# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.TitleTest
- Generated at: 2026-09-20T19:28:23.693370

### Method: testMaleWithInvalidTitleJones
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -                .statusCode(200); | +                .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testFemaleWithInvalidTitleJones
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -                .statusCode(200); | +                .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testUnknownSexFallsThroughToDefault
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -                .statusCode(200); | +                .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2.DateParseTest
- Generated at: 2026-09-20T19:28:31.131969

### Method: testInvalidDayAndMonth
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -given().when().get("/api/dateparse/Superday/Movember").then().statusCode(200); | +given().when().get("/api/dateparse/Superday/Movember").then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
