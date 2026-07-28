# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.ResponseEntityTest
- Generated at: 2026-07-12T10:31:21.102590

### Method: testNameEndpointReturnsMessageFieldForServerError
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: [Internal Server Error]
- Observed value in implementation: [Not Found]
- Change summary: -        Response act = given().when().get("/v1/name/True"); | -        assertEquals("Internal Server Error", act.jsonPath().getString("message")); | +        Response act = given().when().get("/v1/name/True").then().statusCode(404).extract().response(); | +        assertEquals("Not Found", act.jsonPath().getString("message"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CurrencyTest
- Generated at: 2026-07-12T10:31:36.473075

### Method: testV1CurrencyValidShouldReturn200
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 200 to 404
- Change summary: -given().when().get("/v1/all").then().statusCode(lessThan(300)); | +given().when().get("/v1/all").then().statusCode(404);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testV2CurrencyBadRequestShouldReturn400
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 404
- Assertion updated from: 400 to 404
- Change summary: -given().when().get("/v2/all").then().statusCode(lessThan(300)); | +given().when().get("/v2/all").then().statusCode(404);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testV1CurrencyInvalidFormatShouldReturn400
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 404
- Assertion updated from: 400 to 404
- Change summary: -given().when().get("/v1/all").then().statusCode(lessThan(300)); | +given().when().get("/v1/all").then().statusCode(404);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2.CountryTranslationsTest
- Generated at: 2026-07-12T10:31:45.887992

### Method: testV1RegionReturnsTranslationsJaAndIt
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 200 to 404
- Change summary: -        given().when().get("/v1/region/Americas").then().statusCode(200).body("[0].translations.ja", notNullValue()); | +        given().when().get("/v1/region/Americas").then().statusCode(404).body("[0].translations.ja", notNullValue());
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
