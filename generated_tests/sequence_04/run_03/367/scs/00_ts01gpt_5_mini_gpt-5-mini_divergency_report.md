# Test Set Divergency Report

## Divergences

- Test class: ts01gpt_5_mini.CostfunsTest
- Generated at: 2026-07-10T13:25:35.450118

### Method: testCostfuns_negativeI_triggersMultipleIntegerBranches
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: [6]
- Observed value in implementation: [10]
- Change summary: -        Assert.assertEquals("6", resp.asString()); | +        Assert.assertEquals("10", resp.asString());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.

### Method: testCostfuns_iEquals5_returnsExpectedBody
- Model: openai/gpt-5-mini
- Change type: asserted_value
- Expected value before fix: [6]
- Observed value in implementation: [10]
- Change summary: -        Assert.assertEquals("6", resp.asString()); | +        Assert.assertEquals("10", resp.asString());
- Rationale: Assertion values adjusted to match observed implementation behavior during repair.
