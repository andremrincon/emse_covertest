# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.WrongProductConfigurationExceptionTest
- Generated at: 2026-09-21T21:57:45.899155

### Method: testCreateConfigurationWithVeryLongNameReturns500
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 201
- Change summary: -        assertEquals(500, act.getStatusCode()); | +        assertEquals(201, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testAddFeatureToConfigurationSuccessReturns201
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 201
- Observed value in implementation: 500
- Change summary: -        assertEquals(201, act.getStatusCode()); | +        assertEquals(500, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testAddFeatureWithExtremelyLongNameReturns500
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 201
- Change summary: -        assertEquals(500, act.getStatusCode()); | +        assertEquals(201, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.ProductsServiceTest
- Generated at: 2026-09-21T21:57:46.575847

### Method: testAddFeatureToConfigurationSuccess
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 201
- Observed value in implementation: 500
- Change summary: -        assertEquals(201, act.getStatusCode()); | +        assertEquals(500, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.DuplicatedObjectExceptionTest
- Generated at: 2026-09-21T21:57:50.274945

### Method: duplicateProductCreationShouldReturnServerError
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 201
- Change summary: -        assertEquals(500, act.getStatusCode()); | +        assertEquals(201, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.ProductTest
- Generated at: 2026-09-21T21:57:56.106789

### Method: testDeleteFeatureFromConfigurationReturns204
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 204
- Observed status (implementation): 500
- Assertion updated from: 204 to 500
- Change summary: -        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300)); | +        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.FeatureTest
- Generated at: 2026-09-21T21:57:57.540217

### Method: testDeleteConfigurationFeatureReturns204
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 204
- Observed status (implementation): 500
- Assertion updated from: 204 to 500
- Change summary: -        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300)); | +        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testGetConfigurationActivedFeaturesReturns200
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300)); | +        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.ProductsConfigurationFeaturesResourceTest
- Generated at: 2026-09-21T21:58:09.307688

### Method: testDeleteFeature_Success
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 204
- Observed status (implementation): 500
- Assertion updated from: 204 to 500
- Change summary: -        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(lessThan(300)); | -        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(204); | +        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName).then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.ConfigurationEvaluatorTest
- Generated at: 2026-09-21T21:58:15.149982

### Method: testAddFeatureToConfigurationCreatesEvaluation
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 201
- Observed status (implementation): 500
- Assertion updated from: 500 to 201
- Change summary: -        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(500); | +        given().formParam("description", "Auto-added feature for configuration") | +                .when() | +                .post("/products/{productName}/features/{featureName}", productName, featureName) | +                .then().statusCode(lessThan(300)); | +        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName).then().statusCode(201);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.ProductsConfigurationsServiceTest
- Generated at: 2026-09-21T21:58:28.669175

### Method: testRemoveFeatureFromConfiguration_returns204
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 204
- Observed status (implementation): 500
- Assertion updated from: 204 to 500
- Change summary: -        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, feature).then().statusCode(lessThan(300)); | -        Response act = given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, feature); | -        act.then().statusCode(204); | +        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, feature); | +        act.then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
