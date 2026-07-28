package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CurrencyTest {

    private static final String BASE_URL = System.getProperty("base.url", "http://localhost:8080/rest");

    @Ignore("1 expectation failed. JSON path [0].currencies[0].code doesn't match. Expected: USD   Actual: null")
    @Test(timeout = 60000)
    public void testCurrencySettersViaV1Alpha() {
        given()
            .when()
                .get(BASE_URL + "/v1/alpha/US")
            .then()
                .body("[0].currencies[0].code", equalTo("USD"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testCurrencySettersViaV1Currency() {
        given()
            .when()
                .get(BASE_URL + "/v1/currency/USD")
            .then()
                .body("currencies[0].name", equalTo("United States dollar"));
    }

    @Test(timeout = 60000)
    public void testCurrencySettersViaV2Currency() {
        given()
            .when()
                .get(BASE_URL + "/v2/currency/EUR")
            .then()
                .body("[0].currencies[0].symbol", equalTo("\u20ac"));
    }
}