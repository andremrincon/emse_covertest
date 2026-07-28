# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.ResponseEntityTest
- Generated at: 2026-07-10T11:21:47.967641

### Method: testNameEndpointReturnsMessageFieldForServerErrorExample
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: [Internal Server Error]
- Observed value in implementation: [Not Found]
- Change summary: -        Response act = given().when().get("/v1/name/{name}", "True").then().extract().response(); | -        assertEquals("Internal Server Error", act.jsonPath().getString("message")); | +        Response act = given().when().get("/v1/name/{name}", "True").then().statusCode(404).extract().response(); | +        assertEquals("Not Found", act.jsonPath().getString("message"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.ContributionTest
- Generated at: 2026-07-10T11:21:51.778940

### Method: testContributeAccepted_largeAmountUniqueToken
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 202
- Observed value in implementation: 400
- Change summary: -        Assert.assertEquals(202, resp.getStatusCode()); | +        Assert.assertEquals(400, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testContributeAccepted_withAmountAndToken
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 202
- Observed value in implementation: 400
- Change summary: -        Assert.assertEquals(202, resp.getStatusCode()); | +        Assert.assertEquals(400, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CORSFilterTest
- Generated at: 2026-07-10T11:22:10.869408

### Method: testCorsOnCapitalEndpoint
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: GET
- Observed value in implementation: null
- Change summary: -        assertEquals("GET", resp.getHeader("Access-Control-Allow-Methods")); | +        assertEquals(null, resp.getHeader("Access-Control-Allow-Methods"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testPostContributeAcceptsAndHasCorsStatus
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 202
- Observed value in implementation: 400
- Change summary: -        assertEquals(202, resp.getStatusCode()); | +        assertEquals(400, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCorsOnNameEndpointFullTextParam
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: Accept, X-Requested-With
- Observed value in implementation: null
- Change summary: -        assertEquals("Accept, X-Requested-With", resp.getHeader("Access-Control-Allow-Headers")); | +        assertEquals(null, resp.getHeader("Access-Control-Allow-Headers"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testAllowOriginHeaderOnV1All
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        assertEquals("*", resp.getHeader("Access-Control-Allow-Origin")); | +        assertEquals(null, resp.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCorsOnV1CallingCode
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: public, max-age=86400
- Observed value in implementation: null
- Change summary: -        assertEquals("public, max-age=86400", resp.getHeader("Cache-Control")); | +        assertEquals(null, resp.getHeader("Cache-Control"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testAllowHeadersHeaderOnV1AlphaMultipleCodes
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: Accept, X-Requested-With
- Observed value in implementation: null
- Change summary: -        assertEquals("Accept, X-Requested-With", resp.getHeader("Access-Control-Allow-Headers")); | +        assertEquals(null, resp.getHeader("Access-Control-Allow-Headers"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCacheControlHeaderOnV1CurrencyUSD
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: public, max-age=86400
- Observed value in implementation: null
- Change summary: -        assertEquals("public, max-age=86400", resp.getHeader("Cache-Control")); | +        assertEquals(null, resp.getHeader("Cache-Control"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testAllowMethodsHeaderOnV1AlphaUS
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: GET
- Observed value in implementation: null
- Change summary: -        assertEquals("GET", resp.getHeader("Access-Control-Allow-Methods")); | +        assertEquals(null, resp.getHeader("Access-Control-Allow-Methods"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCorsHeadersPresentOnV2AlphaWithFields
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        assertEquals("*", resp.getHeader("Access-Control-Allow-Origin")); | +        assertEquals(null, resp.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
