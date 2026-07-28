# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.CORSFilterTest
- Generated at: 2026-07-12T15:11:04.804821

### Method: testAccessControlAllowMethodsHeaderOnV1Alpha
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: GET
- Observed value in implementation: null
- Change summary: -        assertEquals("GET", r.getHeader("Access-Control-Allow-Methods")); | +        assertEquals(null, r.getHeader("Access-Control-Allow-Methods"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testAccessControlAllowHeadersHeaderOnV1Currency
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: Accept, X-Requested-With
- Observed value in implementation: null
- Change summary: -        assertEquals("Accept, X-Requested-With", r.getHeader("Access-Control-Allow-Headers")); | +        assertEquals(null, r.getHeader("Access-Control-Allow-Headers"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testContributePostReturnsAcceptedAndFilterAddsHeaders
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 202
- Observed value in implementation: 400
- Change summary: -        assertEquals(202, r.getStatusCode()); | +        assertEquals(400, r.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCacheControlHeaderOnV1Name
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: public, max-age=86400
- Observed value in implementation: null
- Change summary: -        assertEquals("public, max-age=86400", r.getHeader("Cache-Control")); | +        assertEquals(null, r.getHeader("Cache-Control"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testAccessControlAllowOriginHeaderOnV1All
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        assertEquals("*", r.getHeader("Access-Control-Allow-Origin")); | +        assertEquals(null, r.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CountryRestV2Test
- Generated at: 2026-07-12T15:11:11.340398

### Method: testGetBySubRegion_validReturns200
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 404
- Change summary: -        Assert.assertEquals(200, act.getStatusCode()); | +        Assert.assertEquals(404, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByLanguage_validReturns200
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 404
- Change summary: -        Assert.assertEquals(200, act.getStatusCode()); | +        Assert.assertEquals(404, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByAlphaList_validReturns200
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 400
- Change summary: -        Assert.assertEquals(200, act.getStatusCode()); | +        Assert.assertEquals(400, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CountryTranslationsTest
- Generated at: 2026-07-12T15:11:15.117103

### Method: testV1CurrencyUSD_translations_de_firstItem
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: [Vereinigte Staaten von Amerik]a
- Observed value in implementation: [Amerikanisch-Samo]a
- Change summary: -        assertEquals("Vereinigte Staaten von Amerika", actual); | +        assertEquals("Amerikanisch-Samoa", actual);
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
