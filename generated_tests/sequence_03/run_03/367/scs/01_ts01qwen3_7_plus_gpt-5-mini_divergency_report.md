# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.CalcTest
- Generated at: 2026-07-09T20:30:39.505005

### Method: testCalcDivideByZero
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -            .statusCode(200); | +            .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.TitleTest
- Generated at: 2026-07-09T20:30:57.849469

### Method: testNoneValidTitle
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -            .basePath(baseUrl) | +            .baseUri(baseUrl) | -            .statusCode(404) | +            .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testMaleValidTitle
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -            .basePath(baseUrl) | +            .baseUri(baseUrl) | -            .statusCode(404) | +            .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testNoneInvalidTitle
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -            .basePath(baseUrl) | +            .baseUri(baseUrl) | -            .statusCode(404) | +            .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testFemaleValidTitle
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -            .basePath(baseUrl) | +            .baseUri(baseUrl) | -            .statusCode(404) | +            .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testFemaleInvalidTitle
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -            .basePath(baseUrl) | +            .baseUri(baseUrl) | -            .statusCode(404) | +            .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testMaleInvalidTitle
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -            .basePath(baseUrl) | +            .baseUri(baseUrl) | -            .statusCode(404) | +            .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
