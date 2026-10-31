# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.CurrencyTest
- Generated at: 2026-09-21T17:21:42.653747

### Method: testV1Alpha_invalidCodes_returns400
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 200
- Change summary: -        Assert.assertEquals(400, resp.getStatusCode()); | +        Assert.assertEquals(200, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV1Currency_numeric_returns400
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 404
- Change summary: -        Assert.assertEquals(400, resp.getStatusCode()); | +        Assert.assertEquals(404, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV1Alpha_multipleCodes_returns200
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 400
- Change summary: -        Assert.assertEquals(200, resp.getStatusCode()); | +        Assert.assertEquals(400, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV2Currency_EUR_badFields_returns400
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 200
- Change summary: -        Assert.assertEquals(400, resp.getStatusCode()); | +        Assert.assertEquals(200, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CountryRestV1Test
- Generated at: 2026-09-21T17:22:00.433013

### Method: testGetBySubregionValidWesternEurope
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 404
- Change summary: -        assertEquals(200, act.statusCode()); | +        assertEquals(404, act.statusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByAlphaInvalidFormat123
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 404
- Change summary: -        assertEquals(400, act.statusCode()); | +        assertEquals(404, act.statusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByAlphaListValidCodes
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 400
- Change summary: -        assertEquals(200, act.statusCode()); | +        assertEquals(400, act.statusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByAlphaListBadCodes123
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 200
- Change summary: -        assertEquals(400, act.statusCode()); | +        assertEquals(200, act.statusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CORSFilterTest
- Generated at: 2026-09-21T17:22:08.512515

### Method: testV2AllFieldsAddsAllowMethodsHeader
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: GET
- Observed value in implementation: null
- Change summary: -        Response act = given().queryParam("fields", "name;capital;region;population;flag").when().get("/v2/all"); | -        assertEquals("GET", act.getHeader("Access-Control-Allow-Methods")); | +        Response act = given().queryParam("fields", "name;capital;region;population;flag").when().get("/v2/all").then().statusCode(200).extract().response(); | +        assertEquals(null, act.getHeader("Access-Control-Allow-Methods"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV1AlphaCodeInvalidFormatProduces400AndCORS
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: Accept, X-Requested-With
- Observed value in implementation: null
- Change summary: -        Response act = given().when().get("/v1/alpha/123"); | -        assertEquals("Accept, X-Requested-With", act.getHeader("Access-Control-Allow-Headers")); | +        Response act = given().when().get("/v1/alpha/123").then().statusCode(400).extract().response(); | +        assertEquals(null, act.getHeader("Access-Control-Allow-Headers"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV1AlphaCodeValidAddsAllowMethods
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: GET
- Observed value in implementation: null
- Change summary: -        Response act = given().when().get("/v1/alpha/US"); | -        assertEquals("GET", act.getHeader("Access-Control-Allow-Methods")); | +        Response act = given().when().get("/v1/alpha/US").then().statusCode(200).extract().response(); | +        assertEquals(null, act.getHeader("Access-Control-Allow-Methods"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV1NameWithFullTextAddsCORS
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        Response act = given().queryParam("fullText", "true").when().get("/v1/name/France"); | -        assertEquals("*", act.getHeader("Access-Control-Allow-Origin")); | +        Response act = given().queryParam("fullText", "true").when().get("/v1/name/France").then().statusCode(200).extract().response(); | +        assertEquals(null, act.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testPostV2ReturnsMethodNotAllowedAndHasCORSHeaders
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: Accept, X-Requested-With
- Observed value in implementation: null
- Change summary: -        Response act = given().body("{\"dummy\":\"value\"}").when().post("/v2"); | -        assertEquals("Accept, X-Requested-With", act.getHeader("Access-Control-Allow-Headers")); | +        Response act = given().body("{\"dummy\":\"value\"}").when().post("/v2").then().statusCode(405).extract().response(); | +        assertEquals(null, act.getHeader("Access-Control-Allow-Headers"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV1AlphaCodesAddsCORSHeaders
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        Response act = given().queryParam("codes", "US,CA").when().get("/v1/alpha"); | -        assertEquals("*", act.getHeader("Access-Control-Allow-Origin")); | +        Response act = given().queryParam("codes", "US,CA").when().get("/v1/alpha").then().statusCode(200).extract().response(); | +        assertEquals(null, act.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV1CallingcodeNotFoundStillHasCacheControl
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: public, max-age=86400
- Observed value in implementation: null
- Change summary: -        Response act = given().when().get("/v1/callingcode/abc"); | -        assertEquals("public, max-age=86400", act.getHeader("Cache-Control")); | +        Response act = given().when().get("/v1/callingcode/abc").then().statusCode(404).extract().response(); | +        assertEquals(null, act.getHeader("Cache-Control"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV1CurrencyValidAddsCacheControl
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: public, max-age=86400
- Observed value in implementation: null
- Change summary: -        Response act = given().when().get("/v1/currency/USD"); | -        assertEquals("public, max-age=86400", act.getHeader("Cache-Control")); | +        Response act = given().when().get("/v1/currency/USD").then().statusCode(200).extract().response(); | +        assertEquals(null, act.getHeader("Cache-Control"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
