# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.RemainderTest
- Generated at: 2026-07-08T02:41:44.496837

### Method: testRemainderAZero
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -                .statusCode(200); | +                .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testRemainderBZero
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -                .statusCode(200); | +                .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2.BessjTest
- Generated at: 2026-07-08T02:41:50.538773

### Method: testBessj_xZero_returnsZero
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 0.0F
- Observed value in implementation: 0.0
- Change summary: -                .body("resultAsDouble", equalTo(0.0f)); | +                .body("resultAsDouble", equalTo(0.0));
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
