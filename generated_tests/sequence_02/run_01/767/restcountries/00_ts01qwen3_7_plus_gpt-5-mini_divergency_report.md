# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.CountryRestV2Test
- Generated at: 2026-07-08T14:55:28.792889

### Method: testGetByAlphaListValid
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -                .statusCode(400); | +                .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.CountryServiceBaseTest
- Generated at: 2026-07-08T14:55:32.414151

### Method: testGetByCodeListValidCodes
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -            .statusCode(400); | +            .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
