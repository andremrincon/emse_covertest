# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini_2.LanguageTest
- Generated at: 2026-09-21T15:35:37.840356

### Method: testV1AlphaCodesQueryNotFound
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 404
- Observed value in implementation: 400
- Change summary: -        assertEquals(404, act.getStatusCode()); | +        assertEquals(400, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV1AlphaAlphacodeBadRequest
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 404
- Change summary: -        assertEquals(400, act.getStatusCode()); | +        assertEquals(404, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV2AlphaAlphacodeBadRequest
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 404
- Change summary: -        assertEquals(400, act.getStatusCode()); | +        assertEquals(404, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testV1AlphaCodesQuerySuccess
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 400
- Change summary: -        assertEquals(200, act.getStatusCode()); | +        assertEquals(400, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini_2.CountryRestV2Test
- Generated at: 2026-09-21T15:35:42.787883

### Method: testGetByAlphaList_unknownCodesReturns404
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 404
- Observed value in implementation: 200
- Change summary: -        assertEquals(404, act.getStatusCode()); | +        assertEquals(200, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini_2.CurrencyTest
- Generated at: 2026-09-21T15:35:45.215960

### Method: testV1AlphaAlphacodeBadRequest
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 404
- Assertion updated from: 404 to 400
- Change summary: -        res.then().statusCode(404); | +        res.then().statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
