# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.RemainderTest
- Generated at: 2026-07-12T16:27:17.694239

### Method: testRemainderZeroA
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -                .statusCode(200); | +                .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testRemainderZeroB
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -                .statusCode(200); | +                .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.GammqTest
- Generated at: 2026-07-12T16:27:19.185085

### Method: testGcfLargeA
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -            .statusCode(200); | +            .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testGserLargeA
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -            .statusCode(200); | +            .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
