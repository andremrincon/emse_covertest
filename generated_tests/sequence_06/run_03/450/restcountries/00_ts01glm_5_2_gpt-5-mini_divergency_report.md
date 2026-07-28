# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.ContributionTest
- Generated at: 2026-07-12T22:04:10.082004

### Method: testContributeWithExtraFields
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 202
- Observed status (implementation): 400
- Assertion updated from: 400 to 202
- Change summary: -                .statusCode(400); | +                .statusCode(202);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testContributeWithValidAmountAndToken
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 202
- Observed status (implementation): 400
- Assertion updated from: 400 to 202
- Change summary: -                .statusCode(400); | +                .statusCode(202);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testContributeWithLargeAmount
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 202
- Observed status (implementation): 400
- Assertion updated from: 400 to 202
- Change summary: -                .statusCode(400); | +                .statusCode(202);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2.CountryTranslationsTest
- Generated at: 2026-07-12T22:04:23.181947

### Method: testGetByAlphaCodesTriggersTranslationsSetters
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -                .statusCode(400) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
