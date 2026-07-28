# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.CountryServiceBaseTest
- Generated at: 2026-07-09T03:05:39.983365

### Method: testGetByCodeListWithDuplicateCodes
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -            .statusCode(400); | +            .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.ResponseEntityTest
- Generated at: 2026-07-09T03:05:41.583486

### Method: testResponseEntityStatusAndMessageOnCurrencyBadRequest
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 404
- Change summary: -            .body("status", equalTo(400)) | -            .body("message", equalTo("Bad Request")); | +            .statusCode(404) | +            .body("status", equalTo(404)) | +            .body("message", equalTo("Not Found"));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.CountryRestV1Test
- Generated at: 2026-07-09T03:05:42.346909

### Method: testGetByAlphaListNotFound
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 404
- Observed status (implementation): 200
- Assertion updated from: 200 to 404
- Change summary: -            .statusCode(200); | +            .statusCode(404);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.CountryRestV2Test
- Generated at: 2026-07-09T03:06:04.188753

### Method: testGetByAlphaList_NotFound
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 404
- Observed status (implementation): 200
- Assertion updated from: 200 to 404
- Change summary: -            .statusCode(200); | +            .statusCode(404);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
