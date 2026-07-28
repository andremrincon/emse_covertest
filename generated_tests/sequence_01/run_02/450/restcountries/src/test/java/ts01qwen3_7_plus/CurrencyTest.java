package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CurrencyTest {

    @Test(timeout = 60000)
    public void testGetAlphaCodeV1() {
        given()
            .when()
                .get("http://localhost:8080/rest/v1/alpha/US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetCurrencyV1() {
        given()
            .when()
                .get("http://localhost:8080/rest/v1/currency/USD")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetCurrencyV2() {
        given()
            .when()
                .get("http://localhost:8080/rest/v2/currency/EUR")
            .then()
                .statusCode(200);
    }
}