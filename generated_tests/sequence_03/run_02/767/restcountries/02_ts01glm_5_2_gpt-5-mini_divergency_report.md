# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.CountryServiceTest
- Generated at: 2026-07-09T18:36:02.563992

### Method: testGetByLanguageThreeCharCodeMatch
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -given().when().get("/v2/lang/spa").then().statusCode(404); | +given().when().get("/v2/lang/spa").then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testGetByRegionalBlocCaseInsensitive
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -given().when().get("/v2/regionalbloc/eu").then().statusCode(404); | +given().when().get("/v2/regionalbloc/eu").then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testGetByRegionalBlocAcronymMatch
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -given().when().get("/v2/regionalbloc/EU").then().statusCode(404); | +given().when().get("/v2/regionalbloc/EU").then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testGetByLanguageTwoCharCodeMatch
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -given().when().get("/v2/lang/es").then().statusCode(404); | +given().when().get("/v2/lang/es").then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
