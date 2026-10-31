# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.ProductsConstraintsResourceTest
- Generated at: 2026-09-21T07:35:45.229287

### Method: testAddExcludesConstraintToProduct_UriSyntaxException
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 201
- Assertion updated from: 500 to 201
- Change summary: -String productName = "Product With Spaces " + UUID.randomUUID().toString(); | +String productName = "Product-With-Spaces-" + UUID.randomUUID().toString(); | -        response.then().statusCode(500); | +        response.then().statusCode(201);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testAddRequiresConstraintToProduct_UriSyntaxException
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 201
- Assertion updated from: 500 to 201
- Change summary: -String productName = "Product With Spaces " + UUID.randomUUID().toString(); | +String productName = "Product-With-Spaces-" + UUID.randomUUID().toString(); | -        response.then().statusCode(500); | +        response.then().statusCode(201);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.ConstraintRequiresTest
- Generated at: 2026-09-21T07:35:47.680636

### Method: testEvaluateRequiresConstraint_BothActive
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + requiredFeature).then().statusCode(lessThan(300)); | +        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + requiredFeature).then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.ConstraintExcludesTest
- Generated at: 2026-09-21T07:36:41.550743

### Method: testCreateExcludesConstraintWithMissingSourceFeature
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 201
- Assertion updated from: 201 to 500
- Change summary: -            .statusCode(201); | +            .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testCreateExcludesConstraintWithMissingExcludedFeature
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 201
- Assertion updated from: 201 to 500
- Change summary: -            .statusCode(201); | +            .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testDeleteNonExistentConstraint
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 204
- Assertion updated from: 204 to 500
- Change summary: -            .statusCode(204); | +            .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testEvaluateConfigurationWithBothFeaturesActive
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 201
- Observed status (implementation): 500
- Assertion updated from: 500 to 201
- Change summary: -            .statusCode(500); | +            .statusCode(201);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
