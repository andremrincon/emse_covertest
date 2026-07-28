package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CurrencyTest {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";

    @Test(timeout = 60000)
    public void testGetV1CurrencyUSD() {
        given()
            .when()
                .get(baseUrl + "/v1/currency/USD")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetV2CurrencyEUR() {
        given()
            .when()
                .get(baseUrl + "/v2/currency/EUR")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetV1AlphaUS() {
        given()
            .when()
                .get(baseUrl + "/v1/alpha/US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetV2AlphaUS() {
        given()
            .when()
                .get(baseUrl + "/v2/alpha/US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetV1All() {
        given()
            .when()
                .get(baseUrl + "/v1/all")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetV2All() {
        given()
            .when()
                .get(baseUrl + "/v2/all")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetV2RegionalBlocEU() {
        given()
            .when()
                .get(baseUrl + "/v2/regionalbloc/EU")
            .then()
                .statusCode(200);
    }
}