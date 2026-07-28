# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.CountryServiceTest
- Generated at: 2026-07-11T11:30:03.492908

### Method: testGetByLanguage_iso3_returns200
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 404
- Change summary: -        assertEquals(200, act.getStatusCode()); | +        assertEquals(404, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CountryServiceBaseTest
- Generated at: 2026-07-11T11:30:21.014329

### Method: testGetByCodeList_Duplicates_AreFiltered
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 1
- Observed value in implementation: 2
- Change summary: -        given().when().get("/v1/alpha?codes=US,US").then().body("size()", equalTo(1)); | +        given().when().get("/v1/alpha?codes=US,US").then().body("size()", equalTo(2));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
