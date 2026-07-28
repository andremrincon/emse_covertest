# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.FeatureTest
- Generated at: 2026-07-09T22:46:08.941995

### Method: testAddFeatureToConfiguration201
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 201
- Observed status (implementation): 500
- Assertion updated from: 500 to 201
- Change summary: +        given().contentType("application/x-www-form-urlencoded").formParam("description", "feature for configuration") | +                .when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300)); | -                .then().statusCode(500); | +                .then().statusCode(201);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.ProductsConfigurationsServiceTest
- Generated at: 2026-07-09T22:46:38.234023

### Method: testAddFeatureToConfiguration_success_returns_201
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 201
- Observed status (implementation): 500
- Assertion updated from: 500 to 201
- Change summary: +        given().when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300)); | -        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(500); | +        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(201);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
