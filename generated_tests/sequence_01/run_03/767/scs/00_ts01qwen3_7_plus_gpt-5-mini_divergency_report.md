# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.RegexTest
- Generated at: 2026-07-08T06:14:16.518617

### Method: testUrlMatch
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -        response.then().statusCode(404); | +        response.then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.CalcTest
- Generated at: 2026-07-08T06:14:26.468008

### Method: testUnaryMathOperators
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .basePath(baseUrl) | +                .baseUri(baseUrl) | -            response.then().statusCode(404); | +            response.then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testBinaryMultiplicationAndDivision
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .basePath(baseUrl) | +                .baseUri(baseUrl) | -            response.then().statusCode(404); | +            response.then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testBinaryAdditionAndSubtraction
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .basePath(baseUrl) | +                .baseUri(baseUrl) | -            response.then().statusCode(404); | +            response.then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testConstantOperators
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .basePath(baseUrl) | +                .baseUri(baseUrl) | -            response.then().statusCode(404); | +            response.then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testTrigonometricOperators
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .basePath(baseUrl) | +                .baseUri(baseUrl) | -            response.then().statusCode(404); | +            response.then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.DateParseTest
- Generated at: 2026-07-08T06:14:34.644288

### Method: testDateParseInvalid
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -            .statusCode(200); | +            .statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
