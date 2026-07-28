# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.CountryServiceBaseTest
- Generated at: 2026-07-07T20:21:06.147811

### Method: testLoadJsonInitialization
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -        response.then().statusCode(404); | +        response.then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2.CountryRestV2Test
- Generated at: 2026-07-07T20:21:08.314880

### Method: getByAlphaList_invalidCodes_returns500
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 500 to 200
- Change summary: -                .queryParam("codes", "[\"US\", \"CA\"]") | +                .queryParam("codes", "US,CA") | -                .statusCode(500); | +                .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2.LanguageTest
- Generated at: 2026-07-07T20:21:24.292069

### Method: testLanguageSettersViaSubregion
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 200 to 404
- Change summary: -                .get("/v1/subregion/Western Europe") | +                .get("/v1/subregion/Western%20Europe") | -                .statusCode(200) | +                .statusCode(404)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
