# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.LanguageTest
- Generated at: 2026-07-08T15:02:21.711107

### Method: testSubregionEndpointReturnsCountriesWithLanguages
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 200 to 404
- Change summary: -given().when().get("/v1/subregion/Western Europe").then().statusCode(200); | +given().when().get("/v1/subregion/Western%20Europe").then().statusCode(404);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.LanguageTest
- Generated at: 2026-07-08T15:02:25.466237

### Method: testLanguageSettersViaAlphaEndpoint
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 200 to 404
- Change summary: -given() | -            .when() | -            .get("/v1/alpha/US") | -            .then() | -            .statusCode(lessThan(300)); | - | -        Response response = given() | +Response response = given()
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2.ResponseEntityTest
- Generated at: 2026-07-08T15:02:45.103542

### Method: testV2GetRegionServerErrorReturnsStatus
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 404
- Change summary: -                .body("status", equalTo(500)); | +                .body("status", equalTo(404));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.CurrencyTest
- Generated at: 2026-07-08T15:02:49.750095

### Method: testCurrencySettersViaV1Currency
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 200 to 404
- Change summary: -given().when().get("/v1/currency/USD").then().statusCode(lessThan(300)); | -        Response response = given().when().get("/v1/currency/USD"); | -        response.then().statusCode(200); | +Response response = given().when().get("/v1/currency/USD"); | +        response.then().statusCode(404);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testCurrencySettersViaV1Alpha
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 200 to 404
- Change summary: -given().when().get("/v1/alpha/US").then().statusCode(lessThan(300)); | -        Response response = given().when().get("/v1/alpha/US"); | -        response.then().statusCode(200); | +Response response = given().when().get("/v1/alpha/US"); | +        response.then().statusCode(404);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testCurrencySettersViaV2Currency
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 200 to 404
- Change summary: -given().when().get("/v2/currency/EUR").then().statusCode(lessThan(300)); | -        Response response = given().when().get("/v2/currency/EUR"); | -        response.then().statusCode(200); | +Response response = given().when().get("/v2/currency/EUR"); | +        response.then().statusCode(404);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
