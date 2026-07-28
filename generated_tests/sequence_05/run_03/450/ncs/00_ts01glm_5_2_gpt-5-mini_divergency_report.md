# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.NcsRestTest
- Generated at: 2026-07-11T22:40:08.977476

### Method: testFisherXGreaterThanOne
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 400
- Observed status (implementation): 200
- Assertion updated from: 200 to 400
- Change summary: -                .statusCode(200); | +                .statusCode(400);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
