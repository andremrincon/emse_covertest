# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.CORSFilterTest
- Generated at: 2026-07-08T01:11:11.991359

### Method: testCorsAllowOriginOnV1All
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        Response response = given().when().get(baseUrl + "/v1/all").then().extract().response(); | -        assertEquals("*", response.getHeader("Access-Control-Allow-Origin")); | +        Response response = given().when().get(baseUrl + "/v1/all").then().statusCode(200).extract().response(); | +        assertNull(response.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCorsAllowOriginOnV2All
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        Response response = given().when().get(baseUrl + "/v2/all").then().extract().response(); | -        assertEquals("*", response.getHeader("Access-Control-Allow-Origin")); | +        Response response = given().when().get(baseUrl + "/v2/all").then().statusCode(200).extract().response(); | +        assertNull(response.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCorsAllowHeadersOnV2Currency
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: Accept, X-Requested-With
- Observed value in implementation: null
- Change summary: -        Response response = given().when().get(baseUrl + "/v2/currency/EUR").then().extract().response(); | -        assertEquals("Accept, X-Requested-With", response.getHeader("Access-Control-Allow-Headers")); | +        Response response = given().when().get(baseUrl + "/v2/currency/EUR").then().statusCode(200).extract().response(); | +        assertNull(response.getHeader("Access-Control-Allow-Headers"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCorsCacheControlOnV1Name
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: public, max-age=86400
- Observed value in implementation: null
- Change summary: -        Response response = given().when().get(baseUrl + "/v1/name/France").then().extract().response(); | -        assertEquals("public, max-age=86400", response.getHeader("Cache-Control")); | +        Response response = given().when().get(baseUrl + "/v1/name/France").then().statusCode(200).extract().response(); | +        assertNull(response.getHeader("Cache-Control"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCorsCacheControlOnV2Name
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: public, max-age=86400
- Observed value in implementation: null
- Change summary: -        Response response = given().when().get(baseUrl + "/v2/name/Germany").then().extract().response(); | -        assertEquals("public, max-age=86400", response.getHeader("Cache-Control")); | +        Response response = given().when().get(baseUrl + "/v2/name/Germany").then().statusCode(200).extract().response(); | +        assertNull(response.getHeader("Cache-Control"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCorsAllowMethodsOnV1Alpha
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: GET
- Observed value in implementation: null
- Change summary: -        Response response = given().when().get(baseUrl + "/v1/alpha/US").then().extract().response(); | -        assertEquals("GET", response.getHeader("Access-Control-Allow-Methods")); | +        Response response = given().when().get(baseUrl + "/v1/alpha/US").then().statusCode(200).extract().response(); | +        assertNull(response.getHeader("Access-Control-Allow-Methods"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCorsAllowMethodsOnV2Alpha
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: GET
- Observed value in implementation: null
- Change summary: -        Response response = given().when().get(baseUrl + "/v2/alpha/US").then().extract().response(); | -        assertEquals("GET", response.getHeader("Access-Control-Allow-Methods")); | +        Response response = given().when().get(baseUrl + "/v2/alpha/US").then().statusCode(200).extract().response(); | +        assertNull(response.getHeader("Access-Control-Allow-Methods"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCorsAllowHeadersOnV1Currency
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: Accept, X-Requested-With
- Observed value in implementation: null
- Change summary: -        Response response = given().when().get(baseUrl + "/v1/currency/USD").then().extract().response(); | -        assertEquals("Accept, X-Requested-With", response.getHeader("Access-Control-Allow-Headers")); | +        Response response = given().when().get(baseUrl + "/v1/currency/USD").then().statusCode(200).extract().response(); | +        assertNull(response.getHeader("Access-Control-Allow-Headers"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.CountryServiceBaseTest
- Generated at: 2026-07-08T01:11:18.896185

### Method: testFulltextSearchByAltSpelling
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -        response.then().statusCode(404); | +        response.then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.CountryRestV1Test
- Generated at: 2026-07-08T01:11:33.440085

### Method: testGetByAlphaList_Valid
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -            .statusCode(400); | +            .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testGetByAlphaList_NotFound
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 404
- Observed status (implementation): 400
- Assertion updated from: 400 to 404
- Change summary: -            .statusCode(400); | +            .statusCode(404);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
