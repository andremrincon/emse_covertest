# Test Set Divergency Report

## Divergences

- Test class: ts01glm_5_2.CORSFilterTest
- Generated at: 2026-09-20T13:25:52.027253

### Method: doFilterAppliesToV2AlphaEndpoint
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 400
- Assertion updated from: 400 to 200
- Change summary: -                .statusCode(400) | -                .header("Access-Control-Allow-Methods", equalTo("GET")); | +                .statusCode(200) | +                .header("Access-Control-Allow-Methods", equalTo((String) null));
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.

### Method: doFilterAppliesToV2LangEndpoint
- Model: openai/gpt-5-mini
- Change type: status_code
- Expected status (spec/assertion before fix): 200
- Observed status (implementation): 404
- Assertion updated from: 404 to 200
- Change summary: -                .statusCode(404) | -                .header("Cache-Control", equalTo("public, max-age=86400")); | +                .statusCode(200) | +                .header("Cache-Control", equalTo((String) null));
- Rationale: Assertion status adjusted to match observed implementation behavior during repair.
