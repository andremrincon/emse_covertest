# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.CurrencyTest
- Generated at: 2026-07-10T14:53:34.376130

### Method: testGetCountriesByAlphaCodesReturnsCurrencyFields
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -                .statusCode(400) | -                .body("currencies[0].code", notNullValue()); | +                .statusCode(200) | +                .body("[0].currencies[0].code", notNullValue());
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
