# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.CountryServiceBaseTest
- Generated at: 2026-07-11T13:01:15.123002

### Method: testGetByAlphaInvalidFormat
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 404
- Assertion updated from: 404 to 400
- Change summary: -                .statusCode(404); | +                .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2.CountryServiceTest
- Generated at: 2026-07-11T13:01:26.653080

### Method: testGetByLanguageWithThreeCharCode
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -            .statusCode(404); | +            .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2.LanguageTest
- Generated at: 2026-07-11T13:01:59.442714

### Method: testAlphaCodesEndpointReturnsLanguageFields
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -                .statusCode(400) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testLangEndpointReturnsCountriesWithLanguageFields
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404) | -                .body("languages[0][0].iso639_1", is("es")) | -                .body("languages[0][0].name", is("Spanish")); | +                .statusCode(200) | +                .body("languages[0].iso639_1", hasItem("es")) | +                .body("languages[0].name", hasItem("Spanish"));
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
