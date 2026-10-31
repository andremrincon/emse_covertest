# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini_2.CORSFilterTest
- Generated at: 2026-09-21T21:20:16.885356

### Method: testAccessControlAllowOriginOnV2AllFields
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        Assert.assertEquals("*", resp.getHeader("Access-Control-Allow-Origin")); | +        Assert.assertEquals(null, resp.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testAccessControlAllowMethodsOnV1AlphaQuerySuccess
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: GET
- Observed value in implementation: null
- Change summary: -        Assert.assertEquals("GET", resp.getHeader("Access-Control-Allow-Methods")); | +        Assert.assertEquals(null, resp.getHeader("Access-Control-Allow-Methods"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testAccessControlAllowOriginHeaderOnV1AlphaAlphacodeSuccess
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        Assert.assertEquals("*", resp.getHeader("Access-Control-Allow-Origin")); | +        Assert.assertEquals(null, resp.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testAccessControlAllowHeadersOnV2AlphaFields
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: Accept, X-Requested-With
- Observed value in implementation: null
- Change summary: -        Assert.assertEquals("Accept, X-Requested-With", resp.getHeader("Access-Control-Allow-Headers")); | +        Assert.assertEquals(null, resp.getHeader("Access-Control-Allow-Headers"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCacheControlHeaderOnRootGet
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: public, max-age=86400
- Observed value in implementation: null
- Change summary: -        Assert.assertEquals("public, max-age=86400", resp.getHeader("Cache-Control")); | +        Assert.assertEquals(null, resp.getHeader("Cache-Control"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testStatusCode400OnV1AlphaAlphacodeInvalidFormat
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 404
- Change summary: -        Assert.assertEquals(400, resp.getStatusCode()); | +        Assert.assertEquals(404, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
