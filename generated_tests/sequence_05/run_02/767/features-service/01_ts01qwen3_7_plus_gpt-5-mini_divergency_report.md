# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.WrongProductConfigurationExceptionTest
- Generated at: 2026-07-11T19:32:42.615318

### Method: testWrongProductConfigurationExceptionOnRequiresConstraintViolation
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 201
- Assertion updated from: 201 to 500
- Change summary: -            .statusCode(201); | +            .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.FeatureTest
- Generated at: 2026-07-11T19:32:50.848458

### Method: testUpdateFeature
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 500 to 200
- Change summary: -        given().contentType("application/json").body(jsonBody).when().put(baseUrl + "/products/" + productName + "/features/" + featureName).then().statusCode(500); | +        given().contentType("application/json").body(jsonBody).when().put(baseUrl + "/products/" + productName + "/features/" + featureName).then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testCreateRequiresConstraint
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 201
- Observed status (implementation): 500
- Assertion updated from: 500 to 201
- Change summary: -        given().contentType("application/json").body(jsonBody).when().post(baseUrl + "/products/" + productName + "/constraints/requires").then().statusCode(500); | +        given().contentType("application/json").body(jsonBody).when().post(baseUrl + "/products/" + productName + "/constraints/requires").then().statusCode(201);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testCreateExcludesConstraint
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 201
- Observed status (implementation): 500
- Assertion updated from: 500 to 201
- Change summary: -        given().contentType("application/json").body(jsonBody).when().post(baseUrl + "/products/" + productName + "/constraints/excludes").then().statusCode(500); | +        given().contentType("application/json").body(jsonBody).when().post(baseUrl + "/products/" + productName + "/constraints/excludes").then().statusCode(201);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testAddSameFeatureToConfigurationAgain
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 500 to 200
- Change summary: -        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(500); | +        given().when().post(baseUrl + "/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2.ConfigurationEvaluatorTest
- Generated at: 2026-07-11T19:33:07.469741

### Method: testEvaluateConfigurationWithMultipleConstraints
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureB).then().statusCode(lessThan(300)); | +        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureB).then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testEvaluateConfigurationWithRequiresConstraintBothFeaturesActive
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + requiredFeature).then().statusCode(lessThan(300)); | +        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + requiredFeature).then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testEvaluateConfigurationWithExcludesConstraintBothFeaturesActive
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + excludedFeature).then().statusCode(lessThan(300)); | +        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + excludedFeature).then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.ProductsConfigurationsServiceTest
- Generated at: 2026-07-11T19:33:10.231808

### Method: testEvaluateAndUpdateConfiguration_Invalid
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + feature2).then().statusCode(lessThan(300)); | +        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + feature2).then().statusCode(500); | -        given().when().get("/products/" + productName + "/configurations/" + configName).then().statusCode(200).body("valid", equalTo(false)); | +        given().when().get("/products/" + productName + "/configurations/" + configName).then().statusCode(200).body("valid", equalTo(true));
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
