# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.NotFoundExceptionMapperTest
- Generated at: 2026-09-21T16:27:50.374566

### Method: testV2AlphaPathNotFoundTriggersMapper_2
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 404
- Observed value in implementation: 400
- Change summary: -        assertEquals(404, act.getStatusCode()); | +        assertEquals(400, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CountryServiceTest
- Generated at: 2026-09-21T16:27:50.782402

### Method: testV1Lang_ThreeLetterCode_Returns200
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 404
- Change summary: -        assertEquals(200, act.getStatusCode()); | +        assertEquals(404, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CountryRestV1Test
- Generated at: 2026-09-21T16:28:06.465311

### Method: testGetByCurrencyBadRequest
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 404
- Change summary: -        assertEquals(400, response.getStatusCode()); | +        assertEquals(404, response.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByNameServerErrorScenario
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 404
- Change summary: -        assertEquals(500, response.getStatusCode()); | +        assertEquals(404, response.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByAlphaBadRequest
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 404
- Change summary: -        assertEquals(400, response.getStatusCode()); | +        assertEquals(404, response.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByAlphaListSuccess
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 400
- Change summary: -        assertEquals(200, response.getStatusCode()); | +        assertEquals(400, response.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
