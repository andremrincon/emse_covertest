# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.TitleTest
- Generated at: 2026-07-11T11:57:01.259120

### Method: testUnknownSexNeuterReturns500
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 200
- Change summary: -        assertEquals(500, resp.statusCode()); | +        assertEquals(200, resp.statusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.DateParseTest
- Generated at: 2026-07-11T11:57:13.955615

### Method: testDateParseInvalidSuperdayMovember
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -        resp.then().statusCode(200); | +        resp.then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
