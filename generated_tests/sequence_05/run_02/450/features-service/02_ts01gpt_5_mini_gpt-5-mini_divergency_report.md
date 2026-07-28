# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.FeatureTest
- Generated at: 2026-07-11T17:43:10.036127

### Method: testAddFeatureToConfigurationReturns201
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 201
- Observed status (implementation): 500
- Assertion updated from: 500 to 201
- Change summary: -        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName); | -        act.then().statusCode(500); | +        Response act = given().formParam("description", "desc").when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configName, featureName); | +        act.then().statusCode(201);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
