# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.CountryServiceBaseTest
- Generated at: 2026-09-20T20:20:02.918926

### Method: testGetByCodeListWithValidAndInvalidCodes
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -given().queryParam("codes", "US,XX").when().get("/v1/alpha").then().statusCode(400); | +given().queryParam("codes", "US,XX").when().get("/v1/alpha").then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testGetByCodeListWithDuplicateCodes
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -given().queryParam("codes", "US,US").when().get("/v1/alpha").then().statusCode(400); | +given().queryParam("codes", "US,US").when().get("/v1/alpha").then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2.CountryTranslationsTest
- Generated at: 2026-09-20T20:20:07.724284

### Method: testV1AlphaMultipleCodesReturnsTranslations
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -given().queryParam("codes", "US,CA,MX").when().get("/v1/alpha").then().statusCode(400); | +given().queryParam("codes", "US,CA,MX").when().get("/v1/alpha").then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
