# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.CountryRestV1Test
- Generated at: 2026-07-10T07:26:28.487113

### Method: testGetByAlphaList_Valid
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -        Response response = given().when().get("/v1/alpha/?codes=US,CA"); | -        response.then().statusCode(400); | +        Response response = given().when().get("/v1/alpha?codes=US,CA"); | +        response.then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testGetByAlphaList_NotFound
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 404
- Observed status (implementation): 400
- Assertion updated from: 400 to 404
- Change summary: -        Response response = given().when().get("/v1/alpha/?codes=XX,YY"); | -        response.then().statusCode(400); | +        Response response = given().when().get("/v1/alpha?codes=XX,YY"); | +        response.then().statusCode(404);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
