# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini_2.WrongProductConfigurationExceptionTest
- Generated at: 2026-09-21T15:20:21.353828

### Method: addConfigurationWithVeryLongNameReturns500
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 201
- Assertion updated from: 201 to 500
- Change summary: -        act.then().statusCode(201); | +        act.then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: addFeatureToProductReturns201
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 201
- Observed status (implementation): 500
- Assertion updated from: 500 to 201
- Change summary: -        act.then().statusCode(500); | +        act.then().statusCode(201);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: addFeatureWithVeryLongNameReturns500
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 201
- Assertion updated from: 201 to 500
- Change summary: -        act.then().statusCode(201); | +        act.then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini_2.ConfigurationEvaluatorTest
- Generated at: 2026-09-21T15:20:25.964183

### Method: addFeatureToConfiguration_shouldReturn201
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 201
- Observed status (implementation): 500
- Assertion updated from: 500 to 201
- Change summary: +        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300)); | -        act.then().statusCode(500); | +        act.then().statusCode(201);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini_2.ProductTest
- Generated at: 2026-09-21T15:20:31.392159

### Method: testGetConfigurationFeatures_success
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300)); | -        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config).then().statusCode(200); | +        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(500); | +        given().when().get("/products/{productName}/configurations/{configurationName}/features", product, config).then().statusCode(lessThan(300));
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testGetProductsFeatures_success
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300)); | -        given().when().get("/products/{productName}/features", product).then().statusCode(200); | +        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(500); | +        given().when().get("/products/{productName}/features", product).then().statusCode(lessThan(300));
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini_2.FeatureTest
- Generated at: 2026-09-21T15:20:37.231736

### Method: testAddFeatureToConfiguration_Returns201
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 201
- Observed status (implementation): 500
- Assertion updated from: 500 to 201
- Change summary: -        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(500); | +        given().contentType("application/x-www-form-urlencoded").formParam("description", "for-config").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300)); | +        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(201);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
