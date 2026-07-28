# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.ProductTest
- Generated at: 2026-07-10T16:12:18.165539

### Method: testAddFeatureToProduct
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 201
- Observed status (implementation): 500
- Assertion updated from: 500 to 201
- Change summary: -            .post("/products/" + productName + "/features/" + featureName) | +            .put("/products/" + productName + "/features/" + featureName) | -            .statusCode(500); | +            .statusCode(201);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
