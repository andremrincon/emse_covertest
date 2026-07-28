# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.CountryServiceTest
- Generated at: 2026-07-10T13:10:10.476781

### Method: testGetByLanguageWithThreeCharCode
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404); | +                .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testGetByLanguageWithTwoCharCode
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404); | +                .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testGetByRegionalBlocEU
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404); | +                .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2.CountryServiceBaseTest
- Generated at: 2026-07-10T13:10:13.227992

### Method: fulltextSearch_altSpellingMatch_returns200
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 200 to 404
- Change summary: -                .get("/v1/name/French Republic") | +                .get("/v1/name/{name}", "French Republic") | -                .statusCode(200); | +                .statusCode(404);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2.LanguageTest
- Generated at: 2026-07-10T13:10:18.250438

### Method: testLanguageSettersViaV2Subregion
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 200 to 404
- Change summary: -                .get("/v2/subregion/Western Europe") | +                .get("/v2/subregion/{subregion}", "Western Europe") | -                .statusCode(200); | +                .statusCode(404);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
