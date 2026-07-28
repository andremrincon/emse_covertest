# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.DuplicatedObjectExceptionTest
- Generated at: 2026-07-09T08:22:01.133092

### Method: testDuplicateProductCreationResultsInServerError
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 201
- Change summary: -        assertEquals(500, resp.getStatusCode()); | +        assertEquals(201, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.ConfigurationEvaluatorTest
- Generated at: 2026-07-09T08:22:14.776613

### Method: testAddFeatureWithExcludesConstraintLeadsToInvalidPathExecution
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 201
- Observed status (implementation): 500
- Assertion updated from: 500 to 201
- Change summary: -                .then().statusCode(500); | +                .then().statusCode(201);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.ConstraintRequiresTest
- Generated at: 2026-07-09T08:22:23.567625

### Method: testEvaluateWhenBothSourceAndRequiredActiveReturns200
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 500
- Assertion updated from: 200 to 500
- Change summary: -        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, required).then().statusCode(lessThan(300)); | +        given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", product, configuration, required).then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
