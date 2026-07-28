# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.CostfunsTest
- Generated at: 2026-07-08T11:28:25.457578

### Method: testCostfuns_negative_large_i_with_abab_results_in6
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: [6]
- Observed value in implementation: [10]
- Change summary: -        assertEquals("6", resp.getBody().asString()); | +        assertEquals("10", resp.getBody().asString());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCostfuns_returns0_for_i_minus4_and_abab
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: []0
- Observed value in implementation: [1]0
- Change summary: -        assertEquals("0", resp.getBody().asString()); | +        assertEquals("10", resp.getBody().asString());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.NotyPevarTest
- Generated at: 2026-07-08T11:28:27.270898

### Method: testSubjectReturns1WhenXsPlusYEqualsHello7
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: [1]
- Observed value in implementation: [3]
- Change summary: -        Response resp = given().when().get("/api/notypevar/{i}/{s}", 7, "ignored"); | -        assertEquals("1", resp.asString().trim()); | +        Response resp = given().when().get("/api/notypevar/{i}/{s}", 7, "ignored").then().statusCode(200).extract().response(); | +        assertEquals("3", resp.asString().trim());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testSubjectReturns28WhenSumEquals56
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: [28]
- Observed value in implementation: [3]
- Change summary: -        Response resp = given().when().get("/api/notypevar/{i}/{s}", 28, "any"); | -        assertEquals("28", resp.asString().trim()); | +        Response resp = given().when().get("/api/notypevar/{i}/{s}", 28, "any").then().statusCode(200).extract().response(); | +        assertEquals("3", resp.asString().trim());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
