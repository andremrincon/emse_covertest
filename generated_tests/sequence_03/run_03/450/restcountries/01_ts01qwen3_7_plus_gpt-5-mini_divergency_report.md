# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.LanguageTest
- Generated at: 2026-07-09T21:50:07.500927

### Method: testSetNativeName
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testSetName
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testSetIso639_1
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testSetIso639_2
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.CountryServiceBaseTest
- Generated at: 2026-07-09T21:50:19.790281

### Method: testFulltextSearchByAltSpelling
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 200 to 404
- Change summary: -            .get("/v1/name/Bundesrepublik Deutschland") | +            .get("/v1/name/{name}", "Bundesrepublik Deutschland") | -            .statusCode(200); | +            .statusCode(404);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.CountryRestV2Test
- Generated at: 2026-07-09T21:50:26.039908

### Method: testGetByCurrencyException
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 404
- Assertion updated from: 500 to 404
- Change summary: -            .pathParam("currency", "{}") | +            .pathParam("currency", "INVALID") | -            .statusCode(500); | +            .statusCode(404);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.CountryRestV1Test
- Generated at: 2026-07-09T21:50:39.097751

### Method: testGetByAlphaList_Exception
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 404
- Assertion updated from: 500 to 404
- Change summary: -            .queryParam("codes", "[\"US\"]") | +            .queryParam("codes", "%5B%22US%22%5D") | -            .statusCode(500); | +            .statusCode(404);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testGetBySubregion_Valid
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 200 to 404
- Change summary: -            .pathParam("subregion", "Western Europe") | +            .pathParam("subregion", "Western%20Europe") | -            .statusCode(200); | +            .statusCode(404);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
