# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.RemainderTest
- Generated at: 2026-07-12T18:08:01.835193

### Method: testRemainderBEqualsZero
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -            .statusCode(200); | +            .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testRemainderAEqualsZero
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -            .statusCode(200); | +            .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.GammqTest
- Generated at: 2026-07-12T18:08:05.779362

### Method: testGserMaxIterations
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -                .statusCode(200); | +                .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
