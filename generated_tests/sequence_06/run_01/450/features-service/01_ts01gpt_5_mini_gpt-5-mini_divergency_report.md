# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.ProductTest
- Generated at: 2026-07-12T11:40:30.476218

### Method: testAddFeatureToConfiguration_returns201
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 201
- Observed value in implementation: 500
- Change summary: -        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature); | +        Response act = given().contentType("application/x-www-form-urlencoded").formParam("description", "desc").when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature);
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
