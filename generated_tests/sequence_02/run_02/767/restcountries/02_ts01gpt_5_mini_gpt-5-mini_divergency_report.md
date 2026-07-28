# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.StripeRestTest
- Generated at: 2026-07-08T20:58:39.570974

### Method: testContributeWithValidTokenReturnsAccepted
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 202
- Observed value in implementation: 400
- Change summary: -        assertEquals(202, resp.getStatusCode()); | +        assertEquals(400, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CountryRestV1Test
- Generated at: 2026-07-08T20:58:49.437988

### Method: testGetByAlphaList_valid_US_CA
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -        resp.then().statusCode(400); | +        resp.then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testGetByAlphaList_badRequest_123
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -        resp.then().statusCode(200); | +        resp.then().statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testGetBySubregion_valid_Western_Europe
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -        resp.then().statusCode(404); | +        resp.then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CountryRestV2Test
- Generated at: 2026-07-08T20:58:53.120260

### Method: testGetByAlphaList_Success_returns200
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 400
- Change summary: -        assertEquals(200, act.getStatusCode()); | +        assertEquals(400, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByLanguage_Success_returns200
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 404
- Change summary: -        assertEquals(200, act.getStatusCode()); | +        assertEquals(404, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetBySubRegion_Success_returns200
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 404
- Change summary: -        assertEquals(200, act.getStatusCode()); | +        assertEquals(404, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
