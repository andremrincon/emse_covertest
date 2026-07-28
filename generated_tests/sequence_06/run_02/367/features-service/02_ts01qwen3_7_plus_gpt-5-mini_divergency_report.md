# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.FeatureTest
- Generated at: 2026-07-12T14:56:57.147492

### Method: testAddSameFeatureToConfiguration
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 201
- Observed status (implementation): 500
- Assertion updated from: 500 to 201
- Change summary: -        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(500); | +        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(201);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
