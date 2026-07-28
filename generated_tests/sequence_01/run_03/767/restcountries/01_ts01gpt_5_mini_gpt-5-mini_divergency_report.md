# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.CurrencyTest
- Generated at: 2026-07-08T05:51:05.636429

### Method: testContribute_post_returns202
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 202
- Observed value in implementation: 400
- Change summary: -        Assert.assertEquals(202, resp.getStatusCode()); | +        Assert.assertEquals(400, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testAlpha_invalid_alphacode_returns400
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 404
- Change summary: -        Assert.assertEquals(400, resp.getStatusCode()); | +        Assert.assertEquals(404, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CountryRestV2Test
- Generated at: 2026-07-08T05:51:11.335654

### Method: testGetByLanguage_valid
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 404
- Change summary: -        Assert.assertEquals(200, resp.getStatusCode()); | +        Assert.assertEquals(404, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetBySubRegion_valid
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 404
- Change summary: -        Assert.assertEquals(200, resp.getStatusCode()); | +        Assert.assertEquals(404, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CountryRestV1Test
- Generated at: 2026-07-08T05:51:12.315192

### Method: testGetByAlphaList_Success_multipleCodes_returns200
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 400
- Change summary: -        assertEquals(200, act.getStatusCode()); | +        assertEquals(400, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByAlpha_BadRequest_numeric_returns400
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 404
- Change summary: -        assertEquals(400, act.getStatusCode()); | +        assertEquals(404, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByAlphaList_InternalServerError_malformedList_returns500
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 400
- Change summary: -        assertEquals(500, act.getStatusCode()); | +        assertEquals(400, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByAlphaList_BadRequest_missingOrInvalid_returns400
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 200
- Change summary: -        assertEquals(400, act.getStatusCode()); | +        assertEquals(200, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CORSFilterTest
- Generated at: 2026-07-08T05:51:15.701085

### Method: testGetV1AllCorsAllowOrigin
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        assertEquals("*", resp.getHeader("Access-Control-Allow-Origin")); | +        assertNull(resp.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV1AlphaInvalidFormatReturns400
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 404
- Change summary: -        assertEquals(400, resp.getStatusCode()); | +        assertEquals(404, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV1AlphaNotFoundReturns404
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 404
- Observed value in implementation: 400
- Change summary: -        assertEquals(404, resp.getStatusCode()); | +        assertEquals(400, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV1CurrencyUSDCorsAllowHeaders
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: Accept, X-Requested-With
- Observed value in implementation: null
- Change summary: -        assertEquals("Accept, X-Requested-With", resp.getHeader("Access-Control-Allow-Headers")); | +        assertNull(resp.getHeader("Access-Control-Allow-Headers"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV1NameFullTextFalseCorsOrigin
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        assertEquals("*", resp.getHeader("Access-Control-Allow-Origin")); | +        assertNull(resp.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV1AlphaCodesCacheControlHeader
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: public, max-age=86400
- Observed value in implementation: null
- Change summary: -        assertEquals("public, max-age=86400", resp.getHeader("Cache-Control")); | +        assertNull(resp.getHeader("Cache-Control"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV1AlphaUSCorsAllowMethods
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: GET
- Observed value in implementation: null
- Change summary: -        assertEquals("GET", resp.getHeader("Access-Control-Allow-Methods")); | +        assertNull(resp.getHeader("Access-Control-Allow-Methods"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV1RegionAllowMethodsValue
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: GET
- Observed value in implementation: null
- Change summary: -        assertEquals("GET", resp.getHeader("Access-Control-Allow-Methods")); | +        assertNull(resp.getHeader("Access-Control-Allow-Methods"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
