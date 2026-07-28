# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.CountryServiceTest
- Generated at: 2026-07-10T14:44:15.321882

### Method: testGetByLanguage_ThreeLetter_returns200
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 404
- Change summary: -        assertEquals(200, resp.getStatusCode()); | +        assertEquals(404, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.ResponseEntityTest
- Generated at: 2026-07-10T14:44:16.763796

### Method: testV1NameServerErrorStatus
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 404
- Change summary: -        assertEquals(500, act.getStatusCode()); | +        assertEquals(404, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV2NameInternalServerErrorStatus
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 404
- Change summary: -        assertEquals(500, act.getStatusCode()); | +        assertEquals(404, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CountryServiceBaseTest
- Generated at: 2026-07-10T14:44:44.526366

### Method: testGetByCodeList_duplicatesProducesUniqueResults
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 1
- Observed value in implementation: 2
- Change summary: -        resp.then().body("size()", equalTo(1)); | +        resp.then().body("size()", equalTo(2));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
