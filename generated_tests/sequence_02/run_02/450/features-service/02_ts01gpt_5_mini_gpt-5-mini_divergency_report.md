# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.ProductTest
- Generated at: 2026-07-08T18:51:16.898054

### Method: testAddFeatureToConfiguration_returns201
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 201
- Observed status (implementation): 500
- Assertion updated from: 500 to 201
- Change summary: +        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300)); | -        act.then().statusCode(500); | +        act.then().statusCode(201);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
