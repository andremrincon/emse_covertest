# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.FeatureTest
- Generated at: 2026-07-07T14:33:19.029728

### Method: testAddFeatureToConfiguration_returns201
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 201
- Observed value in implementation: 500
- Change summary: +        String description = "desc-" + UUID.randomUUID().toString(); | -        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName); | +        Response act = given().contentType("application/x-www-form-urlencoded").formParam("description", description).when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName);
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.ProductTest
- Generated at: 2026-07-07T14:33:46.032765

### Method: testAddFeatureToConfigurationReturns201
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 201
- Observed status (implementation): 500
- Assertion updated from: 500 to 201
- Change summary: +        given().pathParam("productName", product).pathParam("featureName", feature) | +                .contentType("application/x-www-form-urlencoded") | +                .formParam("description", "desc") | +                .when().post("/products/{productName}/features/{featureName}").then().statusCode(lessThan(300)); | -        act.then().statusCode(500); | +        act.then().statusCode(201);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
