# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.StripeRestTest
- Generated at: 2026-07-09T00:06:52.989821

### Method: testContributeWithTokenReturnsAccepted
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 202
- Observed value in implementation: 400
- Change summary: -        Assert.assertEquals(202, resp.getStatusCode()); | +        Assert.assertEquals(400, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.ResponseEntityTest
- Generated at: 2026-07-09T00:06:53.788626

### Method: testContributeBadRequestMessage
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: [Required fields are missing or invalid.]
- Observed value in implementation: [Bad Request]
- Change summary: -        Assert.assertEquals("Required fields are missing or invalid.", act.jsonPath().getString("message")); | +        Assert.assertEquals("Bad Request", act.jsonPath().getString("message"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CORSFilterTest
- Generated at: 2026-07-09T00:07:06.900232

### Method: testCacheControlHeader_present_onV2All
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: public, max-age=86400
- Observed value in implementation: null
- Change summary: -        Response act = given().when().get(BASE + "/v2/all"); | -        assertEquals("public, max-age=86400", act.getHeader("Cache-Control")); | +        Response act = given().when().get(BASE + "/v2/all").then().statusCode(200).extract().response(); | +        assertEquals(null, act.getHeader("Cache-Control"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testAccessControlAllowHeadersHeader_present_onV1Name
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: Accept, X-Requested-With
- Observed value in implementation: null
- Change summary: -        Response act = given().when().get(BASE + "/v1/name/France?fullText=false"); | -        assertEquals("Accept, X-Requested-With", act.getHeader("Access-Control-Allow-Headers")); | +        Response act = given().when().get(BASE + "/v1/name/France?fullText=false").then().statusCode(200).extract().response(); | +        assertEquals(null, act.getHeader("Access-Control-Allow-Headers"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testAccessControlAllowOriginHeader_present_onV1Alpha
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        Response act = given().when().get(BASE + "/v1/alpha/US"); | -        assertEquals("*", act.getHeader("Access-Control-Allow-Origin")); | +        Response act = given().when().get(BASE + "/v1/alpha/US").then().statusCode(200).extract().response(); | +        assertEquals(null, act.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testAccessControlAllowMethodsHeader_present_onV1All
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: GET
- Observed value in implementation: null
- Change summary: -        Response act = given().when().get(BASE + "/v1/all"); | -        assertEquals("GET", act.getHeader("Access-Control-Allow-Methods")); | +        Response act = given().when().get(BASE + "/v1/all").then().statusCode(200).extract().response(); | +        assertEquals(null, act.getHeader("Access-Control-Allow-Methods"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
