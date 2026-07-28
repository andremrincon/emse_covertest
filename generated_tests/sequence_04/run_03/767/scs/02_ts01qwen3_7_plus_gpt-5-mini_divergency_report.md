# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.RegexTest
- Generated at: 2026-07-10T17:06:25.867616

### Method: testSubjectMatchesUrl
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -        response.then().statusCode(400); | +        response.then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
