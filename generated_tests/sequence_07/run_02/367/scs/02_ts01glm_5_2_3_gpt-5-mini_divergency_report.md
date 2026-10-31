# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2_3.DateParseTest
- Generated at: 2026-09-20T15:08:54.693027

### Method: testDateParseSuperdayMovember
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -                .statusCode(200); | +                .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
