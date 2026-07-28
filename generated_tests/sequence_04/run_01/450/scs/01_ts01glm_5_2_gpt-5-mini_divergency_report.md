# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.TitleTest
- Generated at: 2026-07-10T03:40:50.432409

### Method: testUnknownSex
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -                .statusCode(200); | +                .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testFemaleInvalidTitle
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -                .statusCode(200); | +                .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testMaleInvalidTitle
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -                .statusCode(200); | +                .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2.CalcTest
- Generated at: 2026-07-10T03:40:52.355439

### Method: testCalcDivideByZero
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -            .statusCode(200); | +            .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
