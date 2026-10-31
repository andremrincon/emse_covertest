# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.CountryServiceBaseTest
- Generated at: 2026-09-21T07:50:31.089500

### Method: testGetByCodeListWithDuplicates
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -            .statusCode(400); | +            .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.ContributionTest
- Generated at: 2026-09-21T07:50:32.684033

### Method: testValidContribution
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 202
- Observed status (implementation): 400
- Assertion updated from: 400 to 202
- Change summary: -            .statusCode(400); | +            .statusCode(202);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
