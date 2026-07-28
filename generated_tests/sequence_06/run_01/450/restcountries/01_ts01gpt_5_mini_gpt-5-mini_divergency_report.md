# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.ResponseEntityTest
- Generated at: 2026-07-12T11:55:42.769286

### Method: testStatusReturnedForNameServerError_500
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 404
- Change summary: -        when().get("/v1/name/True").then().body("status", equalTo(500)); | +        when().get("/v1/name/True").then().body("status", equalTo(404));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CountryRestV2Test
- Generated at: 2026-07-12T11:56:10.697213

### Method: testGetByAlpha_invalidLength_returns400
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 404
- Change summary: -        assertEquals(400, resp.getStatusCode()); | +        assertEquals(404, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByAlphaList_valid_returns200
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 400
- Change summary: -        assertEquals(200, resp.getStatusCode()); | +        assertEquals(400, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CountryServiceBaseTest
- Generated at: 2026-07-12T11:56:13.419746

### Method: testGetByCodeList_Duplicates_Removed
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 1
- Observed value in implementation: 2
- Change summary: -        resp.then().body("size()", equalTo(1)); | +        resp.then().body("size()", equalTo(2));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
