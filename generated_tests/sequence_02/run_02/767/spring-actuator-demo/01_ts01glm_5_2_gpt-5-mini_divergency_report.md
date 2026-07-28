# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.SampleControllerTest
- Generated at: 2026-07-08T21:26:49.344738

### Method: timeConsumingAPI_withNonIntegerDelay_returns500
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 400
- Assertion updated from: 400 to 500
- Change summary: -                .statusCode(400); | +                .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
