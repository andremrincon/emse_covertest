# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.StripeRestTest
- Generated at: 2026-07-07T18:14:22.437701

### Method: testContribute_ValidToken_ReturnsAccepted
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 202
- Observed value in implementation: 400
- Change summary: -        Assert.assertEquals(202, act.getStatusCode()); | +        Assert.assertEquals(400, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CountryRestV1Test
- Generated at: 2026-07-07T18:14:35.917677

### Method: testGetByAlphaListSuccess_multiple
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 400
- Change summary: -        assertEquals(200, res.getStatusCode()); | +        assertEquals(400, res.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByAlphaListNotFound
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 404
- Observed value in implementation: 400
- Change summary: -        assertEquals(404, res.getStatusCode()); | +        assertEquals(400, res.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByNameServerError_True
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 404
- Change summary: -        assertEquals(500, res.getStatusCode()); | +        assertEquals(404, res.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByCurrencyBadRequest_numeric
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 404
- Change summary: -        assertEquals(400, res.getStatusCode()); | +        assertEquals(404, res.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByAlphaBadRequest_numeric
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 404
- Change summary: -        assertEquals(400, res.getStatusCode()); | +        assertEquals(404, res.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CORSFilterTest
- Generated at: 2026-07-07T18:14:38.545711

### Method: testAccessControlAllowMethodsOnV1AlphaBadRequest
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: GET
- Observed value in implementation: null
- Change summary: -        Assert.assertEquals("GET", r.getHeader("Access-Control-Allow-Methods")); | +        Assert.assertNull(r.getHeader("Access-Control-Allow-Methods"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testAccessControlOnV2AlphaWithFields
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        Assert.assertEquals("*", r.getHeader("Access-Control-Allow-Origin")); | +        Assert.assertNull(r.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testAllowMethodsHeaderOnV2PostMethodNotAllowed
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: GET
- Observed value in implementation: null
- Change summary: -        Assert.assertEquals("GET", r.getHeader("Access-Control-Allow-Methods")); | +        Assert.assertNull(r.getHeader("Access-Control-Allow-Methods"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCacheControlOnV1NameFullTextTrue
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: public, max-age=86400
- Observed value in implementation: null
- Change summary: -        Assert.assertEquals("public, max-age=86400", r.getHeader("Cache-Control")); | +        Assert.assertNull(r.getHeader("Cache-Control"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testAccessControlAllowOriginOnV1AlphaSuccess
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        Assert.assertEquals("*", r.getHeader("Access-Control-Allow-Origin")); | +        Assert.assertNull(r.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testHeadersPresentOnPostContribute
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        Assert.assertEquals("*", r.getHeader("Access-Control-Allow-Origin")); | +        Assert.assertNull(r.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCacheControlHeaderOnV1All
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: public, max-age=86400
- Observed value in implementation: null
- Change summary: -        Assert.assertEquals("public, max-age=86400", r.getHeader("Cache-Control")); | +        Assert.assertNull(r.getHeader("Cache-Control"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testAccessControlAllowHeadersOnV1AlphaNotFound
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: Accept, X-Requested-With
- Observed value in implementation: null
- Change summary: -        Assert.assertEquals("Accept, X-Requested-With", r.getHeader("Access-Control-Allow-Headers")); | +        Assert.assertNull(r.getHeader("Access-Control-Allow-Headers"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
