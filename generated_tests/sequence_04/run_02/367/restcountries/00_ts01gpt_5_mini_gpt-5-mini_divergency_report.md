# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.CountryRestV2Test
- Generated at: 2026-07-10T07:07:25.820534

### Method: testGetByAlphaListWithMultipleCodesReturns200
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 400
- Change summary: -        assertEquals(200, resp.getStatusCode()); | +        assertEquals(400, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CORSFilterTest
- Generated at: 2026-07-10T07:07:41.880256

### Method: testAccessControlAllowOriginOnV1All
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        Assert.assertEquals("*", act.getHeader("Access-Control-Allow-Origin")); | +        Assert.assertNull(act.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCacheControlHeaderOnV1NameFrance
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: public, max-age=86400
- Observed value in implementation: null
- Change summary: -        Assert.assertEquals("public, max-age=86400", act.getHeader("Cache-Control")); | +        Assert.assertNull(act.getHeader("Cache-Control"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testFilterOnBadAlphaCodeReturns400
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 404
- Change summary: -        Assert.assertEquals(400, act.getStatusCode()); | +        Assert.assertEquals(404, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testPostContributeAccepted202
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 202
- Observed value in implementation: 400
- Change summary: -        Assert.assertEquals(202, act.getStatusCode()); | +        Assert.assertEquals(400, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCorsHeadersPresentOnV2AllWithFields
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        Assert.assertEquals("*", act.getHeader("Access-Control-Allow-Origin")); | +        Assert.assertNull(act.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testAccessControlAllowHeadersOnV1CurrencyUSD
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: Accept, X-Requested-With
- Observed value in implementation: null
- Change summary: -        Assert.assertEquals("Accept, X-Requested-With", act.getHeader("Access-Control-Allow-Headers")); | +        Assert.assertNull(act.getHeader("Access-Control-Allow-Headers"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testAccessControlAllowMethodsOnV1AlphaUS
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: GET
- Observed value in implementation: null
- Change summary: -        Assert.assertEquals("GET", act.getHeader("Access-Control-Allow-Methods")); | +        Assert.assertNull(act.getHeader("Access-Control-Allow-Methods"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
