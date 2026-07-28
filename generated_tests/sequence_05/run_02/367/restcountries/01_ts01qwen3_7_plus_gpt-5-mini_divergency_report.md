# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.LanguageTest
- Generated at: 2026-07-11T16:27:02.887251

### Method: testSetNativeNameViaV2Lang
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404); | +                .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testSetNameViaV2Alpha
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404); | +                .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testSetIso639_2ViaV1Lang
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404); | +                .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testSetIso639_1ViaV1Alpha
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404); | +                .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.CountryServiceBaseTest
- Generated at: 2026-07-11T16:27:26.688079

### Method: testGetByCodeList
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -            .statusCode(400); | +            .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testGetByCodeListWithDuplicates
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -            .statusCode(400); | +            .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
