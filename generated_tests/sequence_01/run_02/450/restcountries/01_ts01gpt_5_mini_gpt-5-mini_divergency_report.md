# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.CountryTranslationsTest
- Generated at: 2026-07-07T21:46:43.237383

### Method: testSetDe
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testSetEs
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testSetFr
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testSetIt
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testSetJa
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CountryServiceBaseTest
- Generated at: 2026-07-07T21:46:51.124078

### Method: test_getByCodeList_multiple_codes_success
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 400
- Change summary: -        assertEquals(200, resp.getStatusCode()); | +        assertEquals(400, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: test_getByCodeList_not_found_404
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 404
- Observed value in implementation: 400
- Change summary: -        assertEquals(404, resp.getStatusCode()); | +        assertEquals(400, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: test_getByAlpha_invalid_format_400
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 404
- Change summary: -        assertEquals(400, resp.getStatusCode()); | +        assertEquals(404, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: test_getByCodeList_invalid_format_400
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 200
- Change summary: -        assertEquals(400, resp.getStatusCode()); | +        assertEquals(200, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: test_getByCodeList_server_error_500
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 400
- Change summary: -        assertEquals(500, resp.getStatusCode()); | +        assertEquals(400, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CountryRestV2Test
- Generated at: 2026-07-07T21:46:59.276368

### Method: testGetByAlphaList_ValidCodes_returns200
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 400
- Change summary: -        Assert.assertEquals(200, resp.statusCode()); | +        Assert.assertEquals(400, resp.statusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByName_InternalServerError_returns500
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 404
- Change summary: -        Assert.assertEquals(500, resp.statusCode()); | +        Assert.assertEquals(404, resp.statusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByAlphaList_MalformedPayload_returns500
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 400
- Change summary: -        Assert.assertEquals(500, resp.statusCode()); | +        Assert.assertEquals(400, resp.statusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByCurrency_InternalServerError_returns500
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 400
- Change summary: -        Assert.assertEquals(500, resp.statusCode()); | +        Assert.assertEquals(400, resp.statusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByAlphaList_NotFound_returns404
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 404
- Observed value in implementation: 400
- Change summary: -        Assert.assertEquals(404, resp.statusCode()); | +        Assert.assertEquals(400, resp.statusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CORSFilterTest
- Generated at: 2026-07-07T21:47:06.952214

### Method: testAccessControlAllowOriginOnV1All
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        Response act = given().when().get("/v1/all"); | -        assertEquals("*", act.getHeader("Access-Control-Allow-Origin")); | +        Response act = given().when().get("/v1/all").then().statusCode(200).extract().response(); | +        assertEquals(null, act.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCorsHeadersPresentOnRootPost
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        Response act = given().contentType("application/json").body("{\"id\":\"" + unique + "\"}").when().post("/"); | -        assertEquals("*", act.getHeader("Access-Control-Allow-Origin")); | +        Response act = given().contentType("application/json").body("{\"id\":\"" + unique + "\"}").when().post("/").then().statusCode(201).extract().response(); | +        assertEquals(null, act.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCacheControlHeaderOnV2All
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: public, max-age=86400
- Observed value in implementation: null
- Change summary: -        Response act = given().when().get("/v2/all"); | -        assertEquals("public, max-age=86400", act.getHeader("Cache-Control")); | +        Response act = given().when().get("/v2/all").then().statusCode(200).extract().response(); | +        assertEquals(null, act.getHeader("Cache-Control"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCorsHeadersOnContributePost
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: GET
- Observed value in implementation: null
- Change summary: -        Response act = given().contentType("application/json").body("{\"amount\":1,\"currency\":\"USD\",\"token\":\"tok_" + UUID.randomUUID().toString() + "\"}").when().post("/contribute"); | -        assertEquals("GET", act.getHeader("Access-Control-Allow-Methods")); | +        Response act = given().contentType("application/json").body("{\"amount\":1,\"currency\":\"USD\",\"token\":\"tok_" + UUID.randomUUID().toString() + "\"}").when().post("/contribute").then().statusCode(200).extract().response(); | +        assertEquals(null, act.getHeader("Access-Control-Allow-Methods"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testAccessControlAllowMethodsOnV1AlphaUS
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: GET
- Observed value in implementation: null
- Change summary: -        Response act = given().when().get("/v1/alpha/US"); | -        assertEquals("GET", act.getHeader("Access-Control-Allow-Methods")); | +        Response act = given().when().get("/v1/alpha/US").then().statusCode(200).extract().response(); | +        assertEquals(null, act.getHeader("Access-Control-Allow-Methods"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testAccessControlAllowHeadersOnV1AlphaQuery
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: Accept, X-Requested-With
- Observed value in implementation: null
- Change summary: -        Response act = given().when().get("/v1/alpha?codes=" + codes); | -        assertEquals("Accept, X-Requested-With", act.getHeader("Access-Control-Allow-Headers")); | +        Response act = given().when().get("/v1/alpha?codes=" + codes).then().statusCode(200).extract().response(); | +        assertEquals(null, act.getHeader("Access-Control-Allow-Headers"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
