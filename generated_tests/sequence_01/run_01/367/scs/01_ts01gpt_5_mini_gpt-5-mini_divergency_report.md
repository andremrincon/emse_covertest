# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.CostfunsTest
- Generated at: 2026-07-07T15:14:33.345205

### Method: testBranchesForVeryNegativeIStillYieldTenOrSixDependingOnS
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: [6]
- Observed value in implementation: [10]
- Change summary: -        assertEquals("6", resp.getBody().asString()); | +        assertEquals("10", resp.getBody().asString());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testReturnsSixWhenIIsFiveAndSIsAbab
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: [6]
- Observed value in implementation: [10]
- Change summary: -        assertEquals("6", resp.getBody().asString()); | +        assertEquals("10", resp.getBody().asString());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testReturnsZeroWhenIIsMinus4AndSIsAbab
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: []0
- Observed value in implementation: [1]0
- Change summary: -        assertEquals("0", resp.getBody().asString()); | +        assertEquals("10", resp.getBody().asString());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CalcTest
- Generated at: 2026-07-07T15:14:39.549634

### Method: testDivideByZero_Returns500
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "divide", "100", "0").then().statusCode(200); | +        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "divide", "100", "0").then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
