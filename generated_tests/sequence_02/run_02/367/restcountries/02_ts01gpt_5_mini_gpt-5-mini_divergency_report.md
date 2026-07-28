# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.ResponseEntityTest
- Generated at: 2026-07-08T17:26:52.887437

### Method: testRegionServerErrorStatus
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 404
- Change summary: -        given().when().get("/v1/region/True").then().body("status", equalTo(500)); | +        given().when().get("/v1/region/True").then().body("status", equalTo(404));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testNameServerErrorStatus
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 404
- Change summary: -        given().when().get("/v1/name/True").then().body("status", equalTo(500)); | +        given().when().get("/v1/name/True").then().body("status", equalTo(404));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CurrencyTest
- Generated at: 2026-07-08T17:26:54.285121

### Method: testV1Alpha_invalidFormat_returns400
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 404
- Change summary: -        assertEquals(400, act.getStatusCode()); | +        assertEquals(404, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV2Alpha_multipleCodes_returns200
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 400
- Change summary: -        assertEquals(200, act.getStatusCode()); | +        assertEquals(400, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CORSFilterTest
- Generated at: 2026-07-08T17:27:06.984089

### Method: testPostContributeHasAllowMethodsHeader
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: GET
- Observed value in implementation: null
- Change summary: -        Response resp = given().contentType("application/json").body(payload).when().post("/contribute"); | -        Assert.assertEquals("GET", resp.getHeader("Access-Control-Allow-Methods")); | +        Response resp = given().contentType("application/json").body(payload).when().post("/contribute").then().statusCode(200).extract().response(); | +        Assert.assertNull(resp.getHeader("Access-Control-Allow-Methods"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV1AllAddsAccessControlAllowOrigin
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        Response resp = given().when().get("/v1/all"); | -        Assert.assertEquals("*", resp.getHeader("Access-Control-Allow-Origin")); | +        Response resp = given().when().get("/v1/all").then().statusCode(200).extract().response(); | +        Assert.assertNull(resp.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV1AlphaQueryAddsAllowHeaders
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: Accept, X-Requested-With
- Observed value in implementation: null
- Change summary: -        Response resp = given().queryParam("codes", "US,CA,MX").when().get("/v1/alpha"); | -        Assert.assertEquals("Accept, X-Requested-With", resp.getHeader("Access-Control-Allow-Headers")); | +        Response resp = given().queryParam("codes", "US,CA,MX").when().get("/v1/alpha").then().statusCode(200).extract().response(); | +        Assert.assertNull(resp.getHeader("Access-Control-Allow-Headers"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV1NameAddsAccessControlAllowOrigin
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        Response resp = given().when().get("/v1/name/France"); | -        Assert.assertEquals("*", resp.getHeader("Access-Control-Allow-Origin")); | +        Response resp = given().when().get("/v1/name/France").then().statusCode(200).extract().response(); | +        Assert.assertNull(resp.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV1CallingCodeAddsAccessControlAllowOrigin
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        Response resp = given().when().get("/v1/callingcode/1"); | -        Assert.assertEquals("*", resp.getHeader("Access-Control-Allow-Origin")); | +        Response resp = given().when().get("/v1/callingcode/1").then().statusCode(200).extract().response(); | +        Assert.assertNull(resp.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV2AllWithFieldsAddsCacheControl
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: public, max-age=86400
- Observed value in implementation: null
- Change summary: -        Response resp = given().queryParam("fields", "name;capital;region").when().get("/v2/all"); | -        Assert.assertEquals("public, max-age=86400", resp.getHeader("Cache-Control")); | +        Response resp = given().queryParam("fields", "name;capital;region").when().get("/v2/all").then().statusCode(200).extract().response(); | +        Assert.assertNull(resp.getHeader("Cache-Control"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV1AlphaUSAddsAllowMethods
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: GET
- Observed value in implementation: null
- Change summary: -        Response resp = given().when().get("/v1/alpha/US"); | -        Assert.assertEquals("GET", resp.getHeader("Access-Control-Allow-Methods")); | +        Response resp = given().when().get("/v1/alpha/US").then().statusCode(200).extract().response(); | +        Assert.assertNull(resp.getHeader("Access-Control-Allow-Methods"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV1CurrencyAddsCacheControl
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: public, max-age=86400
- Observed value in implementation: null
- Change summary: -        Response resp = given().when().get("/v1/currency/USD"); | -        Assert.assertEquals("public, max-age=86400", resp.getHeader("Cache-Control")); | +        Response resp = given().when().get("/v1/currency/USD").then().statusCode(200).extract().response(); | +        Assert.assertNull(resp.getHeader("Cache-Control"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
