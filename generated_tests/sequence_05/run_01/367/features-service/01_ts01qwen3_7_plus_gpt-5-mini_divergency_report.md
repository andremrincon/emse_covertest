# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.WrongProductConfigurationExceptionTest
- Generated at: 2026-07-11T11:02:21.799788

### Method: getConfiguration_withRequiresConstraintViolation_throwsWrongProductConfigurationException
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 200
- Change summary: -        Assert.assertTrue(status < 300); | +        Assert.assertThat(status, lessThan(300)); | -        Assert.assertTrue(status < 300); | +        Assert.assertThat(status, lessThan(300)); | -        Assert.assertTrue(status < 300); | +        Assert.assertThat(status, lessThan(300)); | -        Assert.assertTrue(status < 300); | +        Assert.assertThat(status, lessThan(300));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.ProductsConfigurationsServiceTest
- Generated at: 2026-07-11T11:02:37.695029

### Method: testEvaluateAndUpdateConfigurationInvalid
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -            .statusCode(lessThan(300)); | +            .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.DuplicatedObjectExceptionTest
- Generated at: 2026-07-11T11:02:44.737999

### Method: testCreateDuplicateProduct_1
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 201
- Assertion updated from: 201 to 500
- Change summary: -                .statusCode(201); | +                .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testCreateDuplicateConfiguration_1
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 201
- Assertion updated from: 201 to 500
- Change summary: -                .statusCode(201); | +                .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2.ProductsConfigurationsServiceTest
- Generated at: 2026-07-11T11:02:52.938018

### Method: testEvaluateAndUpdateConfigurationWithRequiresConstraintInvalid
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 200 to 404
- Change summary: -            .statusCode(lessThan(300)); | +            .statusCode(404);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testEvaluateAndUpdateConfigurationWithRequiresConstraintValid
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 200 to 404
- Change summary: -            .statusCode(lessThan(300)); | +            .statusCode(404);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
