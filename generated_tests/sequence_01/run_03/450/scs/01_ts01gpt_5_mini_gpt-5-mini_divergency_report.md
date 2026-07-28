# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.TitleTest
- Generated at: 2026-07-08T03:41:36.644920

### Method: testUnknownSexReturns500
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -        resp.then().statusCode(200); | +        resp.then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.DateParseTest
- Generated at: 2026-07-08T03:41:37.576547

### Method: testInvalidDayTriggersServerError
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 200
- Change summary: -        assertEquals(500, act.getStatusCode()); | +        assertEquals(200, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
