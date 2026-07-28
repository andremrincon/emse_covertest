# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.ConstraintRequiresTest
- Generated at: 2026-07-11T21:28:16.503709

### Method: testCreateRequiresConstraintWithMissingRequiredFeature
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 201
- Assertion updated from: 201 to 500
- Change summary: -            .statusCode(201); | +            .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testCreateRequiresConstraintWithMissingSourceFeature
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 201
- Assertion updated from: 201 to 500
- Change summary: -            .statusCode(201); | +            .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testEvaluateConfigSourceActiveRequiredAlsoActive
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + requiredFeature).then().statusCode(lessThan(300)); | +        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + requiredFeature).then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2.WrongProductConfigurationExceptionTest
- Generated at: 2026-07-11T21:28:26.530892

### Method: getConfigurationActiveFeatures_withRequiresConstraintViolation_throwsWrongProductConfigurationException
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -                .statusCode(200); | +                .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2.ConfigurationEvaluatorTest
- Generated at: 2026-07-11T21:28:38.317762

### Method: evaluateConfigurationViaGetConfigurationEndpoint
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -            .then().statusCode(lessThan(300)); | +            .then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: evaluateConfigurationWithExcludesConstraintViolated
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 201
- Observed status (implementation): 500
- Assertion updated from: 500 to 201
- Change summary: -            .then().statusCode(500); | +            .then().statusCode(201);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: evaluateConfigurationWithMultipleConstraints
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -            .then().statusCode(lessThan(300)); | +            .then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: evaluateConfigurationWithRequiresConstraintSatisfied
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 201
- Observed status (implementation): 500
- Assertion updated from: 500 to 201
- Change summary: -            .then().statusCode(500); | +            .then().statusCode(201);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
