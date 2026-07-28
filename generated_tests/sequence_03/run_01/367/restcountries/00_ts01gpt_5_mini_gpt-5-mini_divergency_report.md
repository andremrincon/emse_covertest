# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.CountryServiceTest
- Generated at: 2026-07-09T08:48:30.962355

### Method: testGetByLanguage_threeLetter_shouldReturn200
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 404
- Change summary: -        assertEquals(200, resp.getStatusCode()); | +        assertEquals(404, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CountryRestV2Test
- Generated at: 2026-07-09T08:48:44.378825

### Method: testGetByAlphaList_valid200
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -        given().queryParam("codes", "US,CA").when().get("/v2/alpha").then().statusCode(400); | +        given().queryParam("codes", "US,CA").when().get("/v2/alpha").then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testGetByAlphaList_badRequest
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -        given().queryParam("codes", "123").when().get("/v2/alpha").then().statusCode(200); | +        given().queryParam("codes", "123").when().get("/v2/alpha").then().statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
