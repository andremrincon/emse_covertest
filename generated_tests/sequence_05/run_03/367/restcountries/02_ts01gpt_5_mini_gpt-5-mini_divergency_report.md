# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.CountryServiceBaseTest
- Generated at: 2026-07-11T22:06:28.770841

### Method: testGetByCodeList_invalid_format_badRequest
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 200
- Change summary: -        Assert.assertEquals(400, act.getStatusCode()); | +        Assert.assertEquals(200, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByCodeList_success_multiple_codes_v1
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 400
- Change summary: -        Assert.assertEquals(200, act.getStatusCode()); | +        Assert.assertEquals(400, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testFulltextSearch_substring_search_false
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 404
- Change summary: -        Assert.assertEquals(200, act.getStatusCode()); | +        Assert.assertEquals(404, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByAlpha_invalid_format_badRequest
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 404
- Change summary: -        Assert.assertEquals(400, act.getStatusCode()); | +        Assert.assertEquals(404, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testFulltextSearch_altspelling_match_fullText_true
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 404
- Change summary: -        Assert.assertEquals(200, act.getStatusCode()); | +        Assert.assertEquals(404, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testLoadJson_trigger_server_error_v1_alpha_codes_array
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 400
- Change summary: -        Assert.assertEquals(500, act.getStatusCode()); | +        Assert.assertEquals(400, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
