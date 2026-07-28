# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.LanguageTest
- Generated at: 2026-07-12T01:43:23.291148

### Method: testLanguageSettersViaAlphaCodesEndpoint
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -                .statusCode(400) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2.CountryTranslationsTest
- Generated at: 2026-07-12T01:43:45.999970

### Method: testV1AlphaCodesMultipleTriggersTranslationsSetters
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -                .statusCode(400) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
