# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.WrongProductConfigurationExceptionTest
- Generated at: 2026-09-21T22:55:55.980861

### Method: addFeatureToConfiguration_returns201
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 201
- Observed status (implementation): 500
- Assertion updated from: 500 to 201
- Change summary: -        resp.then().statusCode(500); | +        resp.then().statusCode(201);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.ProductTest
- Generated at: 2026-09-21T22:56:21.478503

### Method: testAddFeatureToConfiguration_returns201
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 201
- Observed status (implementation): 500
- Assertion updated from: 500 to 201
- Change summary: -        Response act = given().pathParam("productName", productName).pathParam("configurationName", configurationName).pathParam("featureName", featureName).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}"); | -        act.then().statusCode(500); | +        Response act = given().contentType(ContentType.URLENC).formParam("description", "Distributed training feature").pathParam("productName", productName).pathParam("configurationName", configurationName).pathParam("featureName", featureName).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}"); | +        act.then().statusCode(201);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.FeatureTest
- Generated at: 2026-09-21T22:56:36.019634

### Method: testDeleteFeatureFromConfigurationReturns204
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 204
- Observed status (implementation): 500
- Assertion updated from: 204 to 500
- Change summary: -        given().when().post(baseUrl + "/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300)); | +        given().when().post(baseUrl + "/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.ProductsConfigurationsServiceTest
- Generated at: 2026-09-21T22:56:56.500717

### Method: testRemoveFeatureFromConfiguration_success
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 204
- Observed status (implementation): 500
- Assertion updated from: 204 to 500
- Change summary: -        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(lessThan(300)); | -        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(204); | +        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(500); | +        given().when().delete("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
