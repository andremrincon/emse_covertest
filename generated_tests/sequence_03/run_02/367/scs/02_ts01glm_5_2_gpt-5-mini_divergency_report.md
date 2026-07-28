# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.DateParseTest
- Generated at: 2026-07-09T15:02:28.048265

### Method: testDateParse_InvalidDay_InvalidMonth_Returns500
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -                .statusCode(200); | +                .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
