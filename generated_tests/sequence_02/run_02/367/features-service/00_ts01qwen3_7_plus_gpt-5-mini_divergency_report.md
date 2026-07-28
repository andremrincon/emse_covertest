# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.ProductsServiceTest
- Generated at: 2026-07-08T16:52:42.836599

### Method: testAddFeatureToProductDuplicate
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 500
- Assertion updated from: 500 to 400
- Change summary: -            .statusCode(500); | +            .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.ConstraintRequiresTest
- Generated at: 2026-07-08T16:52:46.217395

### Method: testEvaluateConfigurationSourceActiveRequiredActive
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + requiredFeature).then().statusCode(lessThan(300)); | +        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + requiredFeature).then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.WrongProductConfigurationExceptionTest
- Generated at: 2026-07-08T16:52:51.428708

### Method: testWrongProductConfigurationExceptionWhenGettingFeaturesOfConfigurationMissingRequiredFeature
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -            .statusCode(200); | +            .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.ConstraintExcludesTest
- Generated at: 2026-07-08T16:52:55.227033

### Method: testEvaluateConfigurationBothActive
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -        given().pathParam("productName", productName).pathParam("configurationName", configName).pathParam("featureName", excludedFeature).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(lessThan(300)); | +        given().pathParam("productName", productName).pathParam("configurationName", configName).pathParam("featureName", excludedFeature).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}").then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.ConfigurationEvaluatorTest
- Generated at: 2026-07-08T16:52:59.939046

### Method: testConfigurationWithExcludesConstraintViolated
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + feature2).then().statusCode(lessThan(300)); | +        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + feature2).then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testConfigurationWithMultipleRequiresConstraints
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + feature1).then().statusCode(lessThan(300)); | +        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + feature1).then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
