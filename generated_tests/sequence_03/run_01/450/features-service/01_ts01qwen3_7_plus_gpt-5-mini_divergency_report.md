# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.ConstraintExcludesTest
- Generated at: 2026-07-09T10:18:07.672847

### Method: testEvaluateConfigurationConflict
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: false
- Observed value in implementation: true
- Change summary: +        given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureB).then().statusCode(lessThan(300)); | -        response.then().body("valid", equalTo(false)); | +        response.then().statusCode(200).body("valid", equalTo(false));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
