# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.NcsRestTest
- Generated at: 2026-07-09T21:18:17.971152

### Method: testFisherWithZeroM
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -            .statusCode(200); | +            .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testFisherWithXGreaterThanOne
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -            .statusCode(200); | +            .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testRemainderWithZeroB
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -            .statusCode(200); | +            .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
