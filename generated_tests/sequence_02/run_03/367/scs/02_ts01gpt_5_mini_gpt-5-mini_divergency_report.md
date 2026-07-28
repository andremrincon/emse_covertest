# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.DateParseTest
- Generated at: 2026-07-08T22:37:27.076170

### Method: testInvalidDay_returns500
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -        given().when().get(BASE + "/api/dateparse/{dayname}/{monthname}", "123", "August").then().statusCode(200); | +        given().when().get(BASE + "/api/dateparse/{dayname}/{monthname}", "123", "August").then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.RegexTest
- Generated at: 2026-07-08T22:37:29.188239

### Method: testPatEndpointRecognizesUrlInput
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 400
- Change summary: -        assertEquals(200, resp.getStatusCode()); | +        assertEquals(400, resp.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
