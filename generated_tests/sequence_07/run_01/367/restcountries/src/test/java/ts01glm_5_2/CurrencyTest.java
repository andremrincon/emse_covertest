package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CurrencyTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testV2CurrencyEndpointReturnsCurrencyCode() {
        given()
                .when()
                .get("/v2/currency/EUR")
                .then()
                .statusCode(200)
                .body("[0].currencies[0].code", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2CurrencyEndpointReturnsCurrencyName() {
        given()
                .when()
                .get("/v2/currency/USD")
                .then()
                .statusCode(200)
                .body("[0].currencies[0].name", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2CurrencyEndpointReturnsCurrencySymbol() {
        given()
                .when()
                .get("/v2/currency/EUR")
                .then()
                .statusCode(200)
                .body("[0].currencies[0].symbol", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2RegionalblocWithCurrenciesFieldReturnsCurrencyCode() {
        given()
                .queryParam("fields", "name;capital;currencies")
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(200)
                .body("[0].currencies[0].code", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2RegionalblocWithCurrenciesFieldReturnsCurrencyName() {
        given()
                .queryParam("fields", "name;capital;currencies")
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(200)
                .body("[0].currencies[0].name", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2RegionalblocWithCurrenciesFieldReturnsCurrencySymbol() {
        given()
                .queryParam("fields", "name;capital;currencies")
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(200)
                .body("[0].currencies[0].symbol", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2AlphaWithCurrenciesFieldReturnsCurrencyCode() {
        given()
                .queryParam("fields", "name;currencies")
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(200)
                .body("currencies[0].code", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2AlphaWithCurrenciesFieldReturnsCurrencyName() {
        given()
                .queryParam("fields", "name;currencies")
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(200)
                .body("currencies[0].name", notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2AlphaWithCurrenciesFieldReturnsCurrencySymbol() {
        given()
                .queryParam("fields", "name;currencies")
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(200)
                .body("currencies[0].symbol", notNullValue());
    }
}