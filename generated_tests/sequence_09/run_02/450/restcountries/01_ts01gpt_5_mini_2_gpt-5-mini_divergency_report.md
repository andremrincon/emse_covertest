# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini_2.CountryRestV1Test
- Generated at: 2026-09-21T19:20:47.242971

### Method: testGetByAlphaList_InternalServerError_ArrayFormat
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 400
- Change summary: -        assertEquals(500, response.getStatusCode()); | +        assertEquals(400, response.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByAlphaList_NotFound_XX_YY_ZZ
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 404
- Observed value in implementation: 400
- Change summary: -        assertEquals(404, response.getStatusCode()); | +        assertEquals(400, response.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByAlphaList_Success_US_CA_MX
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 400
- Change summary: -        assertEquals(200, response.getStatusCode()); | +        assertEquals(400, response.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini_2.CountryRestV2Test
- Generated at: 2026-09-21T19:21:16.840686

### Method: test_getBySubRegion_success_fields
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -        given().queryParam("fields", "name;capital;population").when().get("/v2/subregion/Western%20Europe").then().statusCode(404); | +        given().queryParam("fields", "name;capital;population").when().get("/v2/subregion/Western%20Europe").then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: test_getByLanguage_success_fields
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -        given().queryParam("fields", "name;capital;population").when().get("/v2/lang/Spanish").then().statusCode(404); | +        given().queryParam("fields", "name;capital;population").when().get("/v2/lang/Spanish").then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: test_getByAlphaList_not_found_unknown_code
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 404
- Observed status (implementation): 200
- Assertion updated from: 200 to 404
- Change summary: -        given().queryParam("codes", "ZZ").when().get("/v2/alpha/").then().statusCode(200); | +        given().queryParam("codes", "ZZ").when().get("/v2/alpha/").then().statusCode(404);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
