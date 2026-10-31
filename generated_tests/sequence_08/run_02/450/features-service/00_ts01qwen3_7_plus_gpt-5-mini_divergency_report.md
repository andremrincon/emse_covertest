# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.ProductTest
- Generated at: 2026-09-21T05:05:11.824895

### Method: testHasFeatureNamedFalse
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 404
- Observed status (implementation): 201
- Assertion updated from: 201 to 404
- Change summary: -            .statusCode(201); | +            .statusCode(404);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testFindProductFeatureByNameNotFound
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 404
- Observed status (implementation): 500
- Assertion updated from: 500 to 404
- Change summary: -            .statusCode(500); | +            .statusCode(404);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.ProductConfigurationTest
- Generated at: 2026-09-21T05:05:37.202141

### Method: testHasActiveFeature
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 201
- Observed status (implementation): 500
- Assertion updated from: 500 to 201
- Change summary: -            .statusCode(500); | +            .statusCode(201);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
