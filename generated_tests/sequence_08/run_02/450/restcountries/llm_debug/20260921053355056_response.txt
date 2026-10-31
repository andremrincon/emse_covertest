package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CurrencyTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testV1CurrencySuccess() {
        given()
        .when()
            .get("/v1/currency/USD")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1CurrencyBadRequest() {
        given()
        .when()
            .get("/v1/currency/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1CurrencyNotFound() {
        given()
        .when()
            .get("/v1/currency/XYZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2CurrencySuccess() {
        given()
        .when()
            .get("/v2/currency/EUR")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV2CurrencyBadRequest() {
        given()
        .when()
            .get("/v2/currency/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2CurrencyNotFound() {
        given()
        .when()
            .get("/v2/currency/XYZ")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2RegionalBlocWithCurrencies() {
        given()
        .when()
            .get("/v2/regionalbloc/EU?fields=name;capital;currencies")
        .then()
            .statusCode(200);
    }
}