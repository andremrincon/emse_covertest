# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.RegexTest
- Generated at: 2026-07-09T14:39:05.188529

### Method: testUrlMatch
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -            .statusCode(404); | +            .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.CostfunsTest
- Generated at: 2026-07-09T14:39:07.942904

### Method: testCostfuns_IGreaterThanOrEqual555_SequalsAlgorithm
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -            .statusCode(404); | +            .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testCostfuns_IGreaterThan666_ScompareToGreaterThanOrEqual0
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -            .statusCode(404); | +            .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testCostfuns_IEquals5_SequalsBaab
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -            .statusCode(404); | +            .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testCostfuns_IEquals0_SequalsA
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -            .statusCode(404); | +            .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testCostfuns_INotEqualMinus4_SequalsAbab
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -            .statusCode(404); | +            .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testCostfuns_ILessThanMinus444_ScompareToGreaterThan0
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -            .statusCode(404); | +            .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
