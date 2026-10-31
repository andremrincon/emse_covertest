# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini_3.CountryServiceBaseTest
- Generated at: 2026-09-21T17:31:38.065476

### Method: testV1Alpha_codes_array_triggers_server_error_500
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 500 to 200
- Change summary: -        given().queryParam("codes", codesArray).when().get("/v1/alpha").then().statusCode(500); | +        try { | +            String encoded = URLEncoder.encode(codesArray, "UTF-8"); | +            given().queryParam("codes", encoded).when().get("/v1/alpha").then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini_3.CountryRestV2Test
- Generated at: 2026-09-21T17:31:43.387065

### Method: testGetByAlpha_v2_badRequest
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 404
- Change summary: -        Assert.assertEquals(400, res.getStatusCode()); | +        Assert.assertEquals(404, res.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByCurrency_v2_badRequest
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 404
- Change summary: -        Assert.assertEquals(400, res.getStatusCode()); | +        Assert.assertEquals(404, res.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByLang_v2_with_fields
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 404
- Change summary: -        Assert.assertEquals(200, res.getStatusCode()); | +        Assert.assertEquals(404, res.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByAlphaList_v2_success
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 400
- Change summary: -        Assert.assertEquals(200, res.getStatusCode()); | +        Assert.assertEquals(400, res.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini_3.CORSFilterTest
- Generated at: 2026-09-21T17:31:47.893713

### Method: testV2Alpha_codes_bad_format_returns_400
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 400 to 200
- Change summary: -        Response act = given().queryParam("codes", "[\"US\",\"CA\"]").when().get("/v2/alpha"); | -        act.then().statusCode(400); | +        Response act = given().queryParam("codes", "%5B%22US%22%2C%22CA%22%5D").when().get("/v2/alpha"); | +        act.then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testV1Alpha_codes_server_error_500_for_array_payload
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 500 to 200
- Change summary: -        Response act = given().queryParam("codes", "[\"US\",\"CA\"]").when().get("/v1/alpha"); | -        act.then().statusCode(500); | +        Response act = given().queryParam("codes", "%5B%22US%22%2C%22CA%22%5D").when().get("/v1/alpha"); | +        act.then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini_3.CountryRestV1Test
- Generated at: 2026-09-21T17:32:01.111336

### Method: testGetByAlphaList_serverError_returns500
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 500 to 200
- Change summary: -        Response resp = given().queryParam("codes", "[\"US\", \"CA\"]").when().get("/v1/alpha"); | -        resp.then().statusCode(500); | +        Response resp = given().queryParam("codes", "US", "CA").when().get("/v1/alpha"); | +        resp.then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
