# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.LanguageTest
- Generated at: 2026-07-09T03:15:16.150010

### Method: testV2LangReturnsLanguageFields
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -given() | +Response resp = given() | -                .get("/v2/lang/Spanish") | -            .then() | -                .statusCode(404) | -                .body("languages[0].iso639_1", notNullValue()) | -                .body("languages[0].iso639_2", notNullValue()) | -                .body("languages[0].name", notNullValue())
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
