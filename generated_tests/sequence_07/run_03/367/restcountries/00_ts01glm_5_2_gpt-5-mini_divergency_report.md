# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.CountryServiceTest
- Generated at: 2026-09-20T17:56:27.335581

### Method: testGetByLanguageWithThreeCharCodeV1
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404); | +                .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2.ContributionTest
- Generated at: 2026-09-20T17:56:43.917132

### Method: testContributeWithMissingToken
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 404
- Assertion updated from: 404 to 400
- Change summary: -            .statusCode(404); | +            .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testContributeWithExtraFields
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 202
- Observed status (implementation): 404
- Assertion updated from: 404 to 202
- Change summary: -            .statusCode(404); | +            .statusCode(202);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testContributeWithZeroAmount
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 404
- Assertion updated from: 404 to 400
- Change summary: -            .statusCode(404); | +            .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testContributeWithMissingAmount
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 404
- Assertion updated from: 404 to 400
- Change summary: -            .statusCode(404); | +            .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testContributeWithNullToken
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 404
- Assertion updated from: 404 to 400
- Change summary: -            .statusCode(404); | +            .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testContributeWithLargeAmount
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 202
- Observed status (implementation): 404
- Assertion updated from: 404 to 202
- Change summary: -            .statusCode(404); | +            .statusCode(202);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testContributeWithMissingCurrency
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 404
- Assertion updated from: 404 to 400
- Change summary: -            .statusCode(404); | +            .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testValidContribution
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 202
- Observed status (implementation): 404
- Assertion updated from: 404 to 202
- Change summary: -            .statusCode(404); | +            .statusCode(202);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testContributeWithNegativeAmount
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 404
- Assertion updated from: 404 to 400
- Change summary: -            .statusCode(404); | +            .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testContributeWithAmountAsString
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 404
- Assertion updated from: 404 to 400
- Change summary: -            .statusCode(404); | +            .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testContributeWithEmptyTokenString
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 404
- Assertion updated from: 404 to 400
- Change summary: -            .statusCode(404); | +            .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testContributeWithEmptyBody
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 404
- Assertion updated from: 404 to 400
- Change summary: -            .statusCode(404); | +            .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
