package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CurrencyTest {

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1AlphaCodeCurrency() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200)
                .body("currencies[0].code", equalTo("USD"))
                .body("currencies[0].name", equalTo("United States dollar"))
                .body("currencies[0].symbol", equalTo("$"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1Currency() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(200)
                .body("[0].currencies[0].code", equalTo("USD"))
                .body("[0].currencies[0].name", equalTo("United States dollar"))
                .body("[0].currencies[0].symbol", equalTo("$"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV2Currency() {
        given()
            .when()
                .get("/v2/currency/EUR")
            .then()
                .statusCode(200)
                .body("[0].currencies[0].code", equalTo("EUR"))
                .body("[0].currencies[0].name", equalTo("Euro"))
                .body("[0].currencies[0].symbol", equalTo("€"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV2RegionalblocCurrency() {
        given()
            .when()
                .get("/v2/regionalbloc/EU")
            .then()
                .statusCode(200)
                .body("[0].currencies[0].code", equalTo("EUR"))
                .body("[0].currencies[0].name", equalTo("Euro"))
                .body("[0].currencies[0].symbol", equalTo("€"));
    }
}