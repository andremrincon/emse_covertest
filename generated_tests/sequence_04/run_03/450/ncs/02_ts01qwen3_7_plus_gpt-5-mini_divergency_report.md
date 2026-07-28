# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.RemainderTest
- Generated at: 2026-07-10T14:17:06.782514

### Method: testRemainderNegativeANegativeB
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -            .statusCode(404) | +            .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testRemainderPositiveANegativeB
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -            .statusCode(404) | +            .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testRemainderNegativeAPositiveB
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -            .statusCode(404) | +            .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testRemainderPositiveAPositiveB
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -            .statusCode(404) | +            .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
