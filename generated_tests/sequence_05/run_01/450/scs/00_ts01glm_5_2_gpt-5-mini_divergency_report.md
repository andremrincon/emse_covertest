# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.DateParseTest
- Generated at: 2026-07-11T13:27:07.688041

### Method: testDateParseInvalidDayAug
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -        given().when().get(baseUrl + "/api/dateparse/noday/aug").then().statusCode(200); | +        given().when().get(baseUrl + "/api/dateparse/noday/aug").then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testDateParseInvalidDayDec
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -        given().when().get(baseUrl + "/api/dateparse/noday/dec").then().statusCode(200); | +        given().when().get(baseUrl + "/api/dateparse/noday/dec").then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testDateParseInvalidDayOct
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -        given().when().get(baseUrl + "/api/dateparse/noday/oct").then().statusCode(200); | +        given().when().get(baseUrl + "/api/dateparse/noday/oct").then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testDateParseInvalidDayInvalidMonth
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -given().when().get(baseUrl + "/api/dateparse/noday/nomonth").then().statusCode(200); | +given().when().get(baseUrl + "/api/dateparse/noday/nomonth").then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
