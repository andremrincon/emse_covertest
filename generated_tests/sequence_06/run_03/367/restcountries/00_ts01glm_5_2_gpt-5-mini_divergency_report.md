# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.CountryTranslationsTest
- Generated at: 2026-07-12T20:23:05.340632

### Method: testGetMultipleAlphaCodesTriggersTranslations
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -                .statusCode(400) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2.CurrencyTest
- Generated at: 2026-07-12T20:23:49.166406

### Method: testGetByAlphaCodesReturnsCountriesWithCurrencyFields
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -                .statusCode(400) | -                .body("currencies[0][0].code", notNullValue()) | -                .body("currencies[0][0].name", notNullValue()) | -                .body("currencies[0][0].symbol", notNullValue()); | +                .statusCode(200) | +                .body("[0].currencies[0]['code']", notNullValue()) | +                .body("[0].currencies[0]['name']", notNullValue()) | +                .body("[0].currencies[0]['symbol']", notNullValue());
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
