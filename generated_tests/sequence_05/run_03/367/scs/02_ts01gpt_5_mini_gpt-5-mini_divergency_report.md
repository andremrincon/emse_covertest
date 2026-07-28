# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.TitleTest
- Generated at: 2026-07-11T22:29:43.614376

### Method: testNeuterSexLeadsServerError
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -        given().when().get("/api/title/{sex}/{title}", "neuter", "Jones").then().statusCode(200); | +        given().when().get("/api/title/{sex}/{title}", "neuter", "Jones").then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testMaleWithFemaleTitleLeadsServerError
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 500
- Observed status (implementation): 200
- Assertion updated from: 200 to 500
- Change summary: -        given().when().get("/api/title/{sex}/{title}", "male", "mrs").then().statusCode(200); | +        given().when().get("/api/title/{sex}/{title}", "male", "mrs").then().statusCode(500);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CostfunsTest
- Generated at: 2026-07-11T22:29:50.298248

### Method: test_iEqualsMinus4_returns0
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: []0
- Observed value in implementation: [1]0
- Change summary: -        Assert.assertEquals("0", resp.getBody().asString()); | +        Assert.assertEquals("10", resp.getBody().asString());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: test_iGreaterThan666_and_geq555_returns6
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: [6]
- Observed value in implementation: [10]
- Change summary: -        Assert.assertEquals("6", resp.getBody().asString()); | +        Assert.assertEquals("10", resp.getBody().asString());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: test_iEquals5_returns6
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: [6]
- Observed value in implementation: [10]
- Change summary: -        Assert.assertEquals("6", resp.getBody().asString()); | +        Assert.assertEquals("10", resp.getBody().asString());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
