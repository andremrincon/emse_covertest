# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.StripeRestTest
- Generated at: 2026-07-08T15:08:03.039526

### Method: testValidLookingTokenReturnsAccepted
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 202
- Observed value in implementation: 400
- Change summary: -        assertEquals(202, response.getStatusCode()); | +        assertEquals(400, response.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CORSFilterTest
- Generated at: 2026-07-08T15:08:19.846071

### Method: testAccessControlAllowHeadersHeaderOnV1CurrencySuccess
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: Accept, X-Requested-With
- Observed value in implementation: null
- Change summary: -        assertEquals("Accept, X-Requested-With", act.getHeader("Access-Control-Allow-Headers")); | +        assertEquals(null, act.getHeader("Access-Control-Allow-Headers"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testHeadersOnV2AlphaWithFields
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        assertEquals("*", act.getHeader("Access-Control-Allow-Origin")); | +        assertEquals(null, act.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testAccessControlAllowOriginHeaderOnV1AlphaSuccess
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        assertEquals("*", act.getHeader("Access-Control-Allow-Origin")); | +        assertEquals(null, act.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCacheControlHeaderOnV1AllSuccess
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: public, max-age=86400
- Observed value in implementation: null
- Change summary: -        assertEquals("public, max-age=86400", act.getHeader("Cache-Control")); | +        assertEquals(null, act.getHeader("Cache-Control"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testAccessControlAllowMethodsHeaderOnV1NameSuccess
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: GET
- Observed value in implementation: null
- Change summary: -        assertEquals("GET", act.getHeader("Access-Control-Allow-Methods")); | +        assertEquals(null, act.getHeader("Access-Control-Allow-Methods"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testHeadersOnContributePostBadRequest
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: GET
- Observed value in implementation: null
- Change summary: -        assertEquals("GET", act.getHeader("Access-Control-Allow-Methods")); | +        assertEquals(null, act.getHeader("Access-Control-Allow-Methods"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testHeadersPresentOnV1AlphaNotFound
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: public, max-age=86400
- Observed value in implementation: null
- Change summary: -        assertEquals("public, max-age=86400", act.getHeader("Cache-Control")); | +        assertEquals(null, act.getHeader("Cache-Control"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testHeadersPresentOnV1AlphaBadRequest
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        assertEquals("*", act.getHeader("Access-Control-Allow-Origin")); | +        assertEquals(null, act.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
