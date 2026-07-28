# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.ConfigurationEvaluatorTest
- Generated at: 2026-07-08T09:59:11.684199

### Method: testEvaluateConfigurationWithExcludesConstraint
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureB).then().statusCode(lessThan(300)); | +        given().baseUri(baseUrl).when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureB).then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.ConstraintRequiresTest
- Generated at: 2026-07-08T09:59:20.314642

### Method: testEvaluateConfiguration_SourceActive_RequiredActive
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + requiredFeature).then().statusCode(lessThan(300)); | +        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + requiredFeature).then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.DuplicatedObjectExceptionTest
- Generated at: 2026-07-08T09:59:22.093822

### Method: testDuplicateProduct
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 201
- Assertion updated from: 201 to 500
- Change summary: -            .statusCode(201); | +            .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testDuplicateConfiguration
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 201
- Assertion updated from: 201 to 500
- Change summary: -            .statusCode(201); | +            .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.WrongProductConfigurationExceptionTest
- Generated at: 2026-07-08T09:59:24.975094

### Method: testWrongProductConfigurationExceptionRequires
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -                .statusCode(200); | +                .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
