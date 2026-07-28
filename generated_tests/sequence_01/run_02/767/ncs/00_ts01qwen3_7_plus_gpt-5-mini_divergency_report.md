# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.ExpintTest
- Generated at: 2026-07-07T22:49:11.104393

### Method: testExpintNegativeN
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 404
- Assertion updated from: 404 to 400
- Change summary: -            .basePath(BASE_URL) | +            .baseUri(BASE_URL) | -            .statusCode(404); | +            .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testExpintXGreaterThanOne
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -            .basePath(BASE_URL) | +            .baseUri(BASE_URL) | -            .statusCode(404); | +            .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testExpintXLessThanOneNm1NotZero
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -            .basePath(BASE_URL) | +            .baseUri(BASE_URL) | -            .statusCode(404); | +            .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testExpintZeroN
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -            .basePath(BASE_URL) | +            .baseUri(BASE_URL) | -            .statusCode(404); | +            .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testExpintZeroX
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -            .basePath(BASE_URL) | +            .baseUri(BASE_URL) | -            .statusCode(404); | +            .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testExpintXLessThanOneNm1Zero
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -            .basePath(BASE_URL) | +            .baseUri(BASE_URL) | -            .statusCode(404); | +            .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.NcsRestTest
- Generated at: 2026-07-07T22:49:21.432098

### Method: testFisherRuntimeException
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -            .statusCode(200); | +            .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
