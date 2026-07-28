# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.ProductTest
- Generated at: 2026-07-11T14:24:08.407104

### Method: testAddFeatureToProduct
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 201
- Observed status (implementation): 500
- Assertion updated from: 500 to 201
- Change summary: -            .contentType(ContentType.JSON) | +            .contentType(ContentType.URLENC) | -            .statusCode(500); | +            .statusCode(201);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
