# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.ProductTest
- Generated at: 2026-07-12T23:43:46.121281

### Method: testAddFeatureToConfiguration_returns201
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 201
- Observed status (implementation): 500
- Assertion updated from: 500 to 201
- Change summary: -        Response act = given().when().post(BASE + "/products/" + urlEncode(product) + "/configurations/" + urlEncode(config) + "/features/" + urlEncode(feature)); | -        act.then().statusCode(500); | +        Response act = given().formParam("description", "desc").when().post(BASE + "/products/" + urlEncode(product) + "/configurations/" + urlEncode(config) + "/features/" + urlEncode(feature)); | +        act.then().statusCode(201);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
