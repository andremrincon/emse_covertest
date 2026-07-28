package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("baseURL", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetV1AlphaCodeReturnsCurrency() {
        RestAssured
            .given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetV1CurrencyReturnsCurrency() {
        RestAssured
            .given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetV2AlphaCodeReturnsCurrency() {
        RestAssured
            .given()
            .when()
                .get("/v2/alpha/US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetV2CurrencyReturnsCurrency() {
        RestAssured
            .given()
            .when()
                .get("/v2/currency/EUR")
            .then()
                .statusCode(200);
    }
}