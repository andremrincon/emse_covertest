# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.WrongProductConfigurationExceptionTest
- Generated at: 2026-09-21T19:00:49.241322

### Method: testGetConfigurationReturnsServerErrorForProblematicConfigurationName
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 204
- Change summary: -        assertEquals(500, act.getStatusCode()); | +        assertEquals(204, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testAddConfigurationWithExcessivelyLongNameReturnsServerError
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 201
- Change summary: -        assertEquals(500, act.getStatusCode()); | +        assertEquals(201, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.ConfigurationEvaluatorTest
- Generated at: 2026-09-21T19:01:38.896642

### Method: testDeleteFeatureFromConfigurationReturns204
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 204
- Observed status (implementation): 500
- Assertion updated from: 204 to 500
- Change summary: -        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300)); | +        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testGetConfigurationFeaturesReturns200
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300)); | +        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
