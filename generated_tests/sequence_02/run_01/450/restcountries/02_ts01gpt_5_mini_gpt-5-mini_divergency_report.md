# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.StripeRestTest
- Generated at: 2026-07-08T12:50:34.660262

### Method: testValidContributionReturnsAccepted
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 202
- Observed value in implementation: 400
- Change summary: -        assertEquals(202, act.getStatusCode()); | +        assertEquals(400, act.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.ResponseEntityTest
- Generated at: 2026-07-08T12:50:46.574729

### Method: testGetMessageWhenPostV1NotAllowed
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: Method [not a]llowed
- Observed value in implementation: Method [Not A]llowed
- Change summary: +        r.then().statusCode(405); | -        Assert.assertEquals("Method not allowed", message); | +        Assert.assertEquals("Method Not Allowed", message);
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
- Test class: ts01gpt_5_mini.CountryRestV1Test
- Generated at: 2026-07-08T12:51:13.773172

### Method: testGetByAlphaList_malformed_array_returns500
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 500
- Observed value in implementation: 400
- Change summary: -        assertEquals(500, res.getStatusCode()); | +        assertEquals(400, res.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByAlphaList_bad_format_returns400
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 400
- Observed value in implementation: 200
- Change summary: -        assertEquals(400, res.getStatusCode()); | +        assertEquals(200, res.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testGetByAlphaList_valid_codes_returns200
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: 200
- Observed value in implementation: 400
- Change summary: -        assertEquals(200, res.getStatusCode()); | +        assertEquals(400, res.getStatusCode());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
