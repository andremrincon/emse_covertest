# Test Set Divergency Report

## Divergences

- Test class: ts01qwen3_7_plus.CountryServiceBaseTest
- Generated at: 2026-07-08T10:45:52.102996

### Method: testGetByAlpha2Code
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -        response.then().statusCode(404); | +        response.then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testGetByAlpha3Code
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -        response.then().statusCode(404); | +        response.then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testFulltextSearchExactMatch
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -        response.then().statusCode(404); | +        response.then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testSubstringSearchPartialMatch
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -        response.then().statusCode(404); | +        response.then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testSubstringSearchExactMatch
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -        response.then().statusCode(404); | +        response.then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testGetByCodeListValid
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -        response.then().statusCode(404); | +        response.then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testSubstringSearchAltSpelling
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -        response.then().statusCode(404); | +        response.then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testGetByCodeListDuplicate
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -        response.then().statusCode(404); | +        response.then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testLoadJsonV1All
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -        response.then().statusCode(404); | +        response.then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testLoadJsonV2All
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -        response.then().statusCode(404); | +        response.then().statusCode(200);
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01glm_5_2.CountryTranslationsTest
- Generated at: 2026-07-08T10:45:55.778154

### Method: testSetDeViaV1AlphaCodeGB
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testSetEsViaV1Region
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testSetJaViaV1AlphaCodesUSCA
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testSetDeViaV1Capital
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testSetItViaV1CallingCode
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testSetDeViaV1AlphaCode
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testSetItViaV1All
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testSetFrViaV1AlphaCodeUS
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testSetItViaV1CurrencyEUR
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testSetFrViaV1Currency
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testSetJaViaV1Lang
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testSetJaViaV1Name
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testSetEsViaV1AlphaCodes
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testSetEsViaV1NameFrance
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404) | +                .statusCode(200)
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
- Test class: ts01qwen3_7_plus.CORSFilterTest
- Generated at: 2026-07-08T10:46:20.712427

### Method: testAccessControlAllowHeadersHeader
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 200 to 404
- Change summary: -given().when().get("/v1/all").then().statusCode(lessThan(300)); | -        Response response = given().when().get("/v1/all").then().statusCode(200).extract().response(); | +Response response = given().when().get("/v1/all").then().statusCode(404).extract().response();
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testAccessControlAllowOriginHeader
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 200 to 404
- Change summary: -given().when().get("/v1/all").then().statusCode(lessThan(300)); | -        Response response = given().when().get("/v1/all").then().statusCode(200).extract().response(); | +Response response = given().when().get("/v1/all").then().statusCode(404).extract().response();
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testAccessControlAllowMethodsHeader
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 200 to 404
- Change summary: -given().when().get("/v1/all").then().statusCode(lessThan(300)); | -        Response response = given().when().get("/v1/all").then().statusCode(200).extract().response(); | +Response response = given().when().get("/v1/all").then().statusCode(404).extract().response();
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: testCacheControlHeader
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 200 to 404
- Change summary: -given().when().get("/v1/all").then().statusCode(lessThan(300)); | -        Response response = given().when().get("/v1/all").then().statusCode(200).extract().response(); | +Response response = given().when().get("/v1/all").then().statusCode(404).extract().response();
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
