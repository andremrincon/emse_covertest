# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.ProductsConfigurationFeaturesResourceTest
- Generated at: 2026-09-20T23:44:35.884155

### Method: testAddFeatureToConfiguration_Failure
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 500
- Assertion updated from: 500 to 400
- Change summary: -        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(500); | +        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testDeleteFeatureFromConfiguration_Failure
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 500
- Assertion updated from: 500 to 400
- Change summary: -        given().when().delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(500); | +        given().when().delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
