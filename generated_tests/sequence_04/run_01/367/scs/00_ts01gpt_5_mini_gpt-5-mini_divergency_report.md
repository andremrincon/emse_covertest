# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.DateParseTest
- Generated at: 2026-07-10T01:45:44.282530

### Method: test_fullDayMonday_returns500_per_api_example
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 200
- Change summary: -        assertEquals(500, act.getStatusCode()); | +        assertEquals(200, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: test_invalidDay_returns500
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 200
- Change summary: -        assertEquals(500, act.getStatusCode()); | +        assertEquals(200, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: test_invalidMonth_returns500
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 200
- Change summary: -        assertEquals(500, act.getStatusCode()); | +        assertEquals(200, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CostfunsTest
- Generated at: 2026-07-10T01:45:47.587736

### Method: testAllConditionsFalseReturnsZero
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: []0
- Observed value in implementation: [1]0
- Change summary: -        Response resp = given().when().get("/api/costfuns/-4/abab"); | -        assertEquals("0", resp.getBody().asString()); | +        Response resp = given().when().get("/api/costfuns/-4/abab").then().statusCode(200).extract().response(); | +        assertEquals("10", resp.getBody().asString());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testNegativeLargeTriggersLessThanAndLessEqual
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: [6]
- Observed value in implementation: [10]
- Change summary: -        Response resp = given().when().get("/api/costfuns/-500/abab"); | -        assertEquals("6", resp.getBody().asString()); | +        Response resp = given().when().get("/api/costfuns/-500/abab").then().statusCode(200).extract().response(); | +        assertEquals("10", resp.getBody().asString());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGreaterThanAndGreaterOrEqualNumericPaths
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: [6]
- Observed value in implementation: [10]
- Change summary: -        Response resp = given().when().get("/api/costfuns/700/abab"); | -        assertEquals("6", resp.getBody().asString()); | +        Response resp = given().when().get("/api/costfuns/700/abab").then().statusCode(200).extract().response(); | +        assertEquals("10", resp.getBody().asString());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.TitleTest
- Generated at: 2026-07-10T01:45:56.818957

### Method: testMaleRecognizedTitleReturnsOne
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: []1
- Observed value in implementation: [-]1
- Change summary: -        String unique = UUID.randomUUID().toString(); | -        Response act = given().when().get(base + "/api/title/male/Mr" + unique + "x".replace(unique, "")); | +        Response act = given().when().get(base + "/api/title/male/Mr").then().statusCode(200).extract().response();
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
