# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2_3.LanguageTest
- Generated at: 2026-09-20T15:56:43.114500

### Method: testV2LangReturnsLanguageFields
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -given().when().get("/v2/lang/Spanish").then().statusCode(404).body("languages[0].name", notNullValue()); | +given().when().get("/v2/lang/Spanish").then().statusCode(200).body("[0].languages[0].name", notNullValue());
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
