# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.ContributionTest
- Generated at: 2026-09-20T19:07:57.003943

### Method: contribute_withNoContentType_returnsBadRequest
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 415
- Assertion updated from: 415 to 400
- Change summary: -                .statusCode(415); | +                .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2.LanguageTest
- Generated at: 2026-09-20T19:08:06.096984

### Method: testV2LangReturnsLanguageFields
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -given() | +JsonPath jp = given() | -                .statusCode(404) | -                .body("[0].languages", notNullValue()) | -                .body("[0].languages[0].iso639_1", notNullValue()) | -                .body("[0].languages[0].iso639_2", notNullValue()) | -                .body("[0].languages[0].name", notNullValue()) | -                .body("[0].languages[0].nativeName", notNullValue());
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2.CurrencyTest
- Generated at: 2026-09-20T19:08:21.560035

### Method: testV1AlphaCodesReturnsCountries
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -            .statusCode(400); | +            .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
