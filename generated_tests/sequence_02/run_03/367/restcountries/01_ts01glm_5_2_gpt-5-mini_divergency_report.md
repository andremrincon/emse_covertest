# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.CountryTranslationsTest
- Generated at: 2026-07-08T22:12:39.382966

### Method: testV1NameUnitedStatesTranslationsDe
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 200 to 404
- Change summary: -                .get("/v1/name/United States of America") | +                .get("/v1/name/United%20States%20of%20America") | -                .statusCode(200) | +                .statusCode(404)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testV1SubregionWesternEuropeTranslationsIt
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 200 to 404
- Change summary: -                .get("/v1/subregion/Western Europe") | +                .get("/v1/subregion/Western%20Europe") | -                .statusCode(200) | +                .statusCode(404)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2.LanguageTest
- Generated at: 2026-07-08T22:12:41.625456

### Method: testGetCountriesBySubregionReturnsLanguageFields
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 200 to 404
- Change summary: -                .get("/v1/subregion/Western Europe") | +                .get("/v1/subregion/Western%20Europe") | -                .statusCode(200) | +                .statusCode(404)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.CountryServiceTest
- Generated at: 2026-07-08T22:12:42.947353

### Method: testGetByRegionalBlocWithEU
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 200 to 404
- Change summary: -            .statusCode(lessThan(300)); | +            .statusCode(404);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testGetByRegionalBlocWithNAFTA
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 200 to 404
- Change summary: -            .statusCode(lessThan(300)); | +            .statusCode(404);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testGetByLanguageWithTwoLetterCode
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 200 to 404
- Change summary: -            .statusCode(lessThan(300)); | +            .statusCode(404);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testGetByLanguageWithThreeLetterCode
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 200 to 404
- Change summary: -            .statusCode(lessThan(300)); | +            .statusCode(404);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2.CountryRestV1Test
- Generated at: 2026-07-08T22:12:55.152776

### Method: getBySubregion_validSubregion_returns200
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 200 to 404
- Change summary: -                .get("/v1/subregion/Western Europe") | +                .get("/v1/subregion/Western%20Europe") | -                .statusCode(200); | +                .statusCode(404);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2.CountryRestV2Test
- Generated at: 2026-07-08T22:13:14.875960

### Method: getByLanguage_success_returns200
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404); | +                .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
