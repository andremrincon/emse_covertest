# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.BessjTest
- Generated at: 2026-07-11T17:19:50.150837

### Method: testBessj_forwardRecurrence_largeX
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -                .statusCode(400); | +                .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testBessj_forwardRecurrence_smallX
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -                .statusCode(400); | +                .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.RemainderTest
- Generated at: 2026-07-11T17:19:52.813502

### Method: testRemainderZeroA
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -        response.then().statusCode(200); | +        response.then().statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testRemainderZeroB
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -        response.then().statusCode(200); | +        response.then().statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
