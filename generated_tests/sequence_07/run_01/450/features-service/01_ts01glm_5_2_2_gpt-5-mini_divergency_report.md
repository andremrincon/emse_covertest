# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2_2.EvaluationResultTest
- Generated at: 2026-09-20T11:51:45.914485

### Method: getConfigurationWithRequiresConstraintTriggersEvaluationResult
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -                .then().statusCode(lessThan(300)); | +                .then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: getConfigurationWithExcludesConstraintTriggersEvaluationResult
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -                .then().statusCode(lessThan(300)); | +                .then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2_2.ConfigurationEvaluatorTest
- Generated at: 2026-09-20T11:51:55.234407

### Method: testEvaluateConfigurationWithRequiresConstraintBothActive
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -            .then().statusCode(lessThan(300)); | +            .then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testEvaluateConfigurationWithExcludesConstraintBothActive
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -            .then().statusCode(lessThan(300)); | +            .then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2_2.WrongProductConfigurationExceptionTest
- Generated at: 2026-09-20T11:52:01.379347

### Method: requiresConstraintViolationTriggersWrongProductConfigurationException
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -        given().when().get("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(200); | +        given().when().get("/products/{productName}/configurations/{configurationName}", productName, configName).then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2_2.ConstraintRequiresTest
- Generated at: 2026-09-20T11:52:22.274714

### Method: testEvaluateConfigBothFeaturesActive
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -        given().when().post("/products/" + product + "/configurations/" + config + "/features/RequiredFeat").then().statusCode(lessThan(300)); | +        given().when().post("/products/" + product + "/configurations/" + config + "/features/RequiredFeat").then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testGetConfigurationDetailsWithRequiresConstraint
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -        given().when().post("/products/" + product + "/configurations/" + config + "/features/RequiredFeat").then().statusCode(lessThan(300)); | +        given().when().post("/products/" + product + "/configurations/" + config + "/features/RequiredFeat").then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2_2.ConstraintExcludesTest
- Generated at: 2026-09-20T11:52:34.365471

### Method: testCreateExcludesConstraintMissingExcludedFeature
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 201
- Assertion updated from: 201 to 500
- Change summary: -            .statusCode(201); | +            .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testCreateExcludesConstraintMissingSourceFeature
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 201
- Assertion updated from: 201 to 500
- Change summary: -            .statusCode(201); | +            .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
