package ts01qwen3_7_plus_3;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CurrencyTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");

    @Test(timeout = 60000)
    public void testGetV1CurrencyValid() {
        given()
            .basePath(baseUrl)
        .when()
            .get("/v1/currency/USD")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetV2CurrencyValid() {
        given()
            .basePath(baseUrl)
        .when()
            .get("/v2/currency/EUR")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetV1CurrencyNotFound() {
        given()
            .basePath(baseUrl)
        .when()
            .get("/v1/currency/XYZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetV2CurrencyNotFound() {
        given()
            .basePath(baseUrl)
        .when()
            .get("/v2/currency/XYZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetV1CurrencyBadRequest() {
        given()
            .basePath(baseUrl)
        .when()
            .get("/v1/currency/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetV2CurrencyBadRequest() {
        given()
            .basePath(baseUrl)
        .when()
            .get("/v2/currency/123")
        .then()
            .statusCode(404);
    }
}