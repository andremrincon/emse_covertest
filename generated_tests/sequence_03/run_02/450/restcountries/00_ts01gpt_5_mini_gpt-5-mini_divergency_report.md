# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.NotFoundExceptionMapperTest
- Generated at: 2026-07-09T15:44:56.166405

### Method: testV1AlphaQueryInvalidCodesMapsTo404
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 404
- Observed value in implementation: 400
- Change summary: -        assertEquals(404, act.getStatusCode()); | +        assertEquals(400, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.ContributionTest
- Generated at: 2026-07-09T15:45:04.766613

### Method: testContributeAcceptedWithValidPayload
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 202
- Observed status (implementation): 400
- Assertion updated from: 400 to 202
- Change summary: -        given().contentType(ContentType.JSON).body(payload).when().post("/contribute").then().statusCode(400); | +        given().contentType(ContentType.JSON).body(payload).when().post("/contribute").then().statusCode(202);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testContributeAcceptedWithLargeAmount
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 202
- Observed status (implementation): 400
- Assertion updated from: 400 to 202
- Change summary: -        given().contentType(ContentType.JSON).body(payload).when().post("/contribute").then().statusCode(400); | +        given().contentType(ContentType.JSON).body(payload).when().post("/contribute").then().statusCode(202);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CountryServiceBaseTest
- Generated at: 2026-07-09T15:45:05.939902

### Method: testGetByCodeList_multipleCodes_success
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 400
- Change summary: -        assertEquals(200, act.getStatusCode()); | +        assertEquals(400, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testLoadJson_invalidCodes_serverError
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 400
- Change summary: -        assertEquals(500, act.getStatusCode()); | +        assertEquals(400, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByAlpha_badFormat_400
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 404
- Change summary: -        assertEquals(400, act.getStatusCode()); | +        assertEquals(404, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CORSFilterTest
- Generated at: 2026-07-09T15:45:17.174146

### Method: testAccessControlAllowOriginOnV1All
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        assertEquals("*", resp.getHeader("Access-Control-Allow-Origin")); | +        assertEquals(null, resp.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testFilterAppliedForPostContributeAddsAllowOrigin
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: *
- Observed value in implementation: null
- Change summary: -        assertEquals("*", resp.getHeader("Access-Control-Allow-Origin")); | +        assertEquals(null, resp.getHeader("Access-Control-Allow-Origin"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testAccessControlAllowMethodsOnV1Alpha
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: GET
- Observed value in implementation: null
- Change summary: -        assertEquals("GET", resp.getHeader("Access-Control-Allow-Methods")); | +        assertEquals(null, resp.getHeader("Access-Control-Allow-Methods"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV1AlphaListBadCodesReturns400
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 200
- Change summary: -        assertEquals(400, resp.getStatusCode()); | +        assertEquals(200, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testAccessControlAllowHeadersOnV1Currency
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: Accept, X-Requested-With
- Observed value in implementation: null
- Change summary: -        assertEquals("Accept, X-Requested-With", resp.getHeader("Access-Control-Allow-Headers")); | +        assertEquals(null, resp.getHeader("Access-Control-Allow-Headers"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCacheControlHeaderOnV2Region
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: public, max-age=86400
- Observed value in implementation: null
- Change summary: -        assertEquals("public, max-age=86400", resp.getHeader("Cache-Control")); | +        assertEquals(null, resp.getHeader("Cache-Control"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
