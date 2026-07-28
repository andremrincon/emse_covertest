# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.CountryServiceTest
- Generated at: 2026-07-12T18:56:08.286363

### Method: testGetByLanguage_ThreeLetterCode_shouldReturn200
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 404
- Change summary: -        assertEquals(200, resp.getStatusCode()); | +        assertEquals(404, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CORSFilterTest
- Generated at: 2026-07-12T18:56:26.931914

### Method: testAlphaListEndpointReturns200
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 400
- Change summary: -        Assert.assertEquals(200, resp.getStatusCode()); | +        Assert.assertEquals(400, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV2AllHasCorsHeader
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        Assert.assertEquals("*", resp.getHeader("Access-Control-Allow-Origin")); | +        Assert.assertNull(resp.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCorsHeadersPresentOnPostContribute
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: GET
- Observed value in implementation: null
- Change summary: -        Assert.assertEquals("GET", resp.getHeader("Access-Control-Allow-Methods")); | +        Assert.assertNull(resp.getHeader("Access-Control-Allow-Methods"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testAllowOriginHeaderOnV1Alpha
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        Assert.assertEquals("*", resp.getHeader("Access-Control-Allow-Origin")); | +        Assert.assertNull(resp.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCacheControlHeaderOnV1Currency
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: public, max-age=86400
- Observed value in implementation: null
- Change summary: -        Assert.assertEquals("public, max-age=86400", resp.getHeader("Cache-Control")); | +        Assert.assertNull(resp.getHeader("Cache-Control"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testOptionsRequestIncludesAllowOrigin
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        Assert.assertEquals("*", resp.getHeader("Access-Control-Allow-Origin")); | +        Assert.assertNull(resp.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testAllowMethodsHeaderOnV1All
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: GET
- Observed value in implementation: null
- Change summary: -        Assert.assertEquals("GET", resp.getHeader("Access-Control-Allow-Methods")); | +        Assert.assertNull(resp.getHeader("Access-Control-Allow-Methods"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CountryRestV2Test
- Generated at: 2026-07-12T18:56:28.051411

### Method: testGetByAlphaList_validCodes_returns200
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -        resp.then().statusCode(400); | +        resp.then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
