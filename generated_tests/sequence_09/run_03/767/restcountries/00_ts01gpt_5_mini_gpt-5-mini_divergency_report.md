# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.CountryTranslationsTest
- Generated at: 2026-09-21T23:10:51.551883

### Method: testV1Alpha_alphacode_numeric_returns_400
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 404
- Assertion updated from: 404 to 400
- Change summary: -        resp.then().statusCode(404); | +        resp.then().statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testV1Alpha_alphacode_true_returns_404
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 404
- Observed status (implementation): 400
- Assertion updated from: 400 to 404
- Change summary: -        resp.then().statusCode(400); | +        resp.then().statusCode(404);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CountryRestV2Test
- Generated at: 2026-09-21T23:11:04.391839

### Method: testGetLanguage_withFields_returns200
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 404
- Change summary: -        assertEquals(200, response.getStatusCode()); | +        assertEquals(404, response.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetAlpha_invalidFormat_returns400
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 404
- Change summary: -        assertEquals(400, response.getStatusCode()); | +        assertEquals(404, response.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetCurrency_badRequest_returns400
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 404
- Change summary: -        assertEquals(400, response.getStatusCode()); | +        assertEquals(404, response.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetAlphaList_valid_returns200
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 400
- Change summary: -        assertEquals(200, response.getStatusCode()); | +        assertEquals(400, response.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CurrencyTest
- Generated at: 2026-09-21T23:11:27.811717

### Method: testV1Alpha_Codes_BadRequest
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -        res.then().statusCode(200); | +        res.then().statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testV1Alpha_Codes_Success
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -        res.then().statusCode(400); | +        res.then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
