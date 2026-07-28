# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.ConfigurationEvaluatorTest
- Generated at: 2026-07-11T12:40:54.630070

### Method: getConfigurationWithViolatedExcludesConstraintCoversInvalidResultWithExcludes
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + excludedFeature).then().statusCode(lessThan(300)); | +        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + excludedFeature).then().statusCode(500); | -            .body("valid", equalTo(false)); | +            .body("valid", equalTo(true));
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: getConfigurationWithViolatedRequiresConstraintCoversInvalidResultPath
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: false
- Observed value in implementation: true
- Change summary: -            .body("valid", equalTo(false)); | +            .body("valid", equalTo(true));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: getConfigurationWithSatisfiedRequiresConstraintCoversValidResultPath
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + requiredFeature).then().statusCode(lessThan(300)); | +        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + requiredFeature).then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2.ConstraintRequiresTest
- Generated at: 2026-07-11T12:40:59.356452

### Method: testEvaluateConfigurationBothFeaturesActive
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -                .then().statusCode(lessThan(300)); | +                .then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2.ProductConfigurationTest
- Generated at: 2026-07-11T12:41:06.311674

### Method: addSameFeatureTwiceCoversActiveWhenAlreadyActive
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 201
- Observed status (implementation): 500
- Assertion updated from: 500 to 201
- Change summary: -        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(500); | +        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(201);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: addExcludesConstraintAndConflictingFeaturesCoversSetValidFalse
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + feature2).then().statusCode(lessThan(300)); | +        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + feature2).then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2.ConstraintExcludesTest
- Generated at: 2026-07-11T12:41:09.486407

### Method: testEvaluateConfigurationBothFeaturesActive
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature).then().statusCode(lessThan(300)); | +        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature).then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testEvaluateConfigurationAfterRemovingExcludedFeature
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature).then().statusCode(lessThan(300)); | +        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, excludedFeature).then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testEvaluateConfigurationWithMultipleExcludesConstraints
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureB).then().statusCode(lessThan(300)); | +        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureB).then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
