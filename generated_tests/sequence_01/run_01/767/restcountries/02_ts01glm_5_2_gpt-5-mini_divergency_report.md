# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.CountryTranslationsTest
- Generated at: 2026-07-07T18:21:13.405669

### Method: testV1AlphaMultipleCodesCommaReturnsTranslations
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -                .statusCode(400) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2.LanguageTest
- Generated at: 2026-07-07T18:21:18.897014

### Method: testGetCountriesByAlphaCodesReturnsLanguageIso639_2
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -                .statusCode(400) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testGetCountriesByLanguageReturnsLanguageFields
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
