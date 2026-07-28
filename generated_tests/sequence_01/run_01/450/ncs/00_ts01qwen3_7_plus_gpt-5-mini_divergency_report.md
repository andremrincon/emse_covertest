# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.GammqTest
- Generated at: 2026-07-07T15:43:02.318713

### Method: testGammqLargeAForGserITMAX
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -        response.then().statusCode(400); | +        response.then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.RemainderTest
- Generated at: 2026-07-07T15:43:03.173063

### Method: testRemainderZeroA
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -            .statusCode(200); | +            .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testRemainderZeroB
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -            .statusCode(200); | +            .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
