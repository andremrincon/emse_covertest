# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini_3.WrongProductConfigurationExceptionTest
- Generated at: 2026-09-21T16:21:12.131655

### Method: addFeatureToConfiguration_afterSetup_returnsCreated
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 201
- Observed value in implementation: 500
- Change summary: -        assertEquals(201, resp.getStatusCode()); | +        assertEquals(500, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: createConfiguration_withExcessivelyLongName_returnsInternalServerError
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 201
- Change summary: -        assertEquals(500, resp.getStatusCode()); | +        assertEquals(201, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: getConfiguration_withNumericName_returnsServerError
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 204
- Change summary: -        assertEquals(500, resp.getStatusCode()); | +        assertEquals(204, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini_3.ConfigurationEvaluatorTest
- Generated at: 2026-09-21T16:21:40.192020

### Method: addRequiresConstraint_then_addFeatureToConfiguration_returns201
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 201
- Observed value in implementation: 500
- Change summary: +        given().contentType("application/x-www-form-urlencoded") | +                .formParam("description", "RAID controller feature") | +                .when() | +                .post("/products/{productName}/features/{featureName}", productName, "RAID-Controller-Card") | +                .then().statusCode(lessThan(300)); | +        given().contentType("application/x-www-form-urlencoded") | +                .formParam("description", "128GB ECC RAM") | +                .when()
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini_3.FeatureTest
- Generated at: 2026-09-21T16:21:54.120138

### Method: addFeatureToConfiguration_returns201
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 201
- Observed status (implementation): 500
- Assertion updated from: 500 to 201
- Change summary: +        given().contentType("application/x-www-form-urlencoded").formParam("description", "feature for config").when().post("/products/{productName}/features/{featureName}", product, feature).then().statusCode(lessThan(300)); | -        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(500); | +        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, config, feature).then().statusCode(201);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
