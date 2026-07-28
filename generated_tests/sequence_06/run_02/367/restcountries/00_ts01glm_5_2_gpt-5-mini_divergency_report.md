# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.LanguageTest
- Generated at: 2026-07-12T15:06:02.415934

### Method: testV1AlphaMultipleCodesReturnsLanguageFields
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -                .statusCode(400) | -                .body("languages[0][0].iso639_1", notNullValue()) | -                .body("languages[0][0].name", notNullValue()); | +                .statusCode(200) | +                .body("languages[0].iso639_1", notNullValue()) | +                .body("languages[0].name", notNullValue());
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testV2LangReturnsLanguageFields
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404) | -                .body("languages[0][0].iso639_1", notNullValue()) | -                .body("languages[0][0].iso639_2", notNullValue()) | -                .body("languages[0][0].name", notNullValue()) | -                .body("languages[0][0].nativeName", notNullValue()); | +                .statusCode(200) | +                .body("languages[0].iso639_1", notNullValue()) | +                .body("languages[0].iso639_2", notNullValue())
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
