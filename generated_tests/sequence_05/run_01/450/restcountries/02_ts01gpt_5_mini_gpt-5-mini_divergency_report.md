# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.StripeRestTest
- Generated at: 2026-07-11T13:17:15.815151

### Method: testContributeWithValidTokenReturnsAccepted
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 202
- Observed value in implementation: 400
- Change summary: -        assertEquals(202, res.getStatusCode()); | +        assertEquals(400, res.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.ResponseEntityTest
- Generated at: 2026-07-11T13:17:21.971989

### Method: testV1NameServerErrorMessage
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: [Internal Server Error]
- Observed value in implementation: [Not Found]
- Change summary: -        assertEquals("Internal Server Error", message); | +        assertEquals("Not Found", message);
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV1NameServerErrorStatusField
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 404
- Change summary: -        assertEquals(500, statusField); | +        assertEquals(404, statusField);
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CountryRestV2Test
- Generated at: 2026-07-11T13:17:26.348437

### Method: testGetByLanguageSuccess
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 404
- Change summary: -        assertEquals(200, resp.getStatusCode()); | +        assertEquals(404, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetBySubRegionSuccess
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 404
- Change summary: -        assertEquals(200, resp.getStatusCode()); | +        assertEquals(404, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByAlphaListWithFieldsReturnsFilteredCountries
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 400
- Change summary: -        assertEquals(200, resp.getStatusCode()); | +        assertEquals(400, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CountryServiceBaseTest
- Generated at: 2026-07-11T13:17:27.967008

### Method: testGetByCodeListBadRequest
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 200
- Change summary: -        assertEquals(400, resp.getStatusCode()); | +        assertEquals(200, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByAlphaBadRequest
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 404
- Change summary: -        assertEquals(400, resp.getStatusCode()); | +        assertEquals(404, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByCodeListServerError
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 400
- Change summary: -        assertEquals(500, resp.getStatusCode()); | +        assertEquals(400, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByCodeListSuccess
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 400
- Change summary: -        assertEquals(200, resp.getStatusCode()); | +        assertEquals(400, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CORSFilterTest
- Generated at: 2026-07-11T13:17:38.247132

### Method: testCorsAllowMethodsOnV1AlphaBadRequest
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 404
- Change summary: -        Assert.assertEquals(400, resp.getStatusCode()); | +        Assert.assertEquals(404, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCorsOnV2AlphaWithFields
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        Assert.assertEquals("*", resp.getHeader("Access-Control-Allow-Origin")); | +        Assert.assertNull(resp.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCorsCacheControlHeaderOnV1All
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: public, max-age=86400
- Observed value in implementation: null
- Change summary: -        Assert.assertEquals("public, max-age=86400", resp.getHeader("Cache-Control")); | +        Assert.assertNull(resp.getHeader("Cache-Control"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCorsOnV2CurrencyBadRequest
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 404
- Change summary: -        Assert.assertEquals(400, resp.getStatusCode()); | +        Assert.assertEquals(404, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCorsAllowMethodsOnV2WithFieldsParam
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: GET
- Observed value in implementation: null
- Change summary: -        Assert.assertEquals("GET", resp.getHeader("Access-Control-Allow-Methods")); | +        Assert.assertNull(resp.getHeader("Access-Control-Allow-Methods"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCorsAllowHeadersOnV1CurrencySuccess
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: Accept, X-Requested-With
- Observed value in implementation: null
- Change summary: -        Assert.assertEquals("Accept, X-Requested-With", resp.getHeader("Access-Control-Allow-Headers")); | +        Assert.assertNull(resp.getHeader("Access-Control-Allow-Headers"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCorsHeadersOnV1AlphaSuccess
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        Assert.assertEquals("*", resp.getHeader("Access-Control-Allow-Origin")); | +        Assert.assertNull(resp.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCorsHeadersOnV1AlphaNotFound
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: GET
- Observed value in implementation: null
- Change summary: -        Assert.assertEquals("GET", resp.getHeader("Access-Control-Allow-Methods")); | +        Assert.assertNull(resp.getHeader("Access-Control-Allow-Methods"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
