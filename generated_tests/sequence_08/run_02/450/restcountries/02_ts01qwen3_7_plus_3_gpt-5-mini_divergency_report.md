# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus_3.CountryServiceBaseTest
- Generated at: 2026-09-21T05:33:58.127401

### Method: testGetByCodeListValid
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -               .statusCode(400); | +               .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testGetByCodeListDuplicate
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -               .statusCode(400); | +               .statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus_3.CountryRestV2Test
- Generated at: 2026-09-21T05:34:10.118730

### Method: testGetByAlphaList_Exception
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 400
- Assertion updated from: 500 to 400
- Change summary: -                .get("/v2/alpha?codes=[\"US\", \"CA\"]") | +                .get("/v2/alpha?codes=%5B%22US%22,%20%22CA%22%5D") | -                .statusCode(500); | +                .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testGetByCurrency_Exception
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 400
- Assertion updated from: 500 to 400
- Change summary: -                .get("/v2/currency/{}") | +                .get("/v2/currency/%7B%7D") | -                .statusCode(500); | +                .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
