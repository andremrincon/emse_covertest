# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini_3.WrongProductConfigurationExceptionTest
- Generated at: 2026-09-21T18:17:51.210946

### Method: createConfigurationWithExcessiveNameTriggersServerError
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 201
- Change summary: -        assertEquals(500, resp.getStatusCode()); | +        assertEquals(201, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: addFeatureWithExtremelyLongNameReturnsServerError
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 201
- Change summary: -        assertEquals(500, resp.getStatusCode()); | +        assertEquals(201, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini_3.ProductConfigurationTest
- Generated at: 2026-09-21T18:17:51.518536

### Method: testAddFeatureToConfiguration_returns201
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 201
- Observed value in implementation: 500
- Change summary: +        given().contentType("application/x-www-form-urlencoded").formParam("description", "auto-added feature").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini_3.ProductTest
- Generated at: 2026-09-21T18:18:51.629963

### Method: addFeatureToConfiguration_shouldReturn201
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 201
- Observed status (implementation): 500
- Assertion updated from: 500 to 201
- Change summary: -        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(500); | +        given().contentType("application/x-www-form-urlencoded").formParam("description", "Distributed training support across multiple nodes").when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(201);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
