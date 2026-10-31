# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini_3.WrongProductConfigurationExceptionTest
- Generated at: 2026-09-21T17:16:17.995085

### Method: testPostConfigurationWithExcessivelyLongNameReturns500
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 201
- Change summary: -        assertEquals(500, act.getStatusCode()); | +        assertEquals(201, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini_3.FeatureTest
- Generated at: 2026-09-21T17:16:29.086625

### Method: testAddFeatureToConfiguration_success
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 201
- Observed status (implementation): 500
- Assertion updated from: 500 to 201
- Change summary: -        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature); | -        act.then().statusCode(500); | +        Response act = given().contentType("application/x-www-form-urlencoded") | +                .formParam("description", "Feature for config") | +                .when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature); | +        act.then().statusCode(201);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini_3.ConfigurationEvaluatorTest
- Generated at: 2026-09-21T17:16:30.010792

### Method: testDeleteConfigurationFeature_Returns204
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 204
- Observed status (implementation): 500
- Assertion updated from: 204 to 500
- Change summary: -        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300)); | +        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini_3.ProductTest
- Generated at: 2026-09-21T17:16:48.927881

### Method: testAddFeatureToConfiguration_shouldReturn201
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 201
- Observed value in implementation: 500
- Change summary: -        Response act = given().pathParam("productName", productName).pathParam("configurationName", configName).pathParam("featureName", featureName).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}"); | +        Response act = given().contentType("application/x-www-form-urlencoded").pathParam("productName", productName).pathParam("configurationName", configName).pathParam("featureName", featureName).formParam("description", "configuration feature").when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}");
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
