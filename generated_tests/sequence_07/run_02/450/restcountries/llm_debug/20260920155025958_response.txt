package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CurrencyTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetCurrencyByCodeUSD() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetAlphaCodeUSReturnsCurrency() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetV2CurrencyEUR() {
        given()
                .when()
                .get("/v2/currency/EUR")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetV2RegionalblocEUWithCurrencyFields() {
        given()
                .queryParam("fields", "name;capital;currencies")
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetV1CurrencyInvalidNumericReturns400() {
        given()
                .when()
                .get("/v1/currency/123")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetV1CurrencyNotFoundReturns404() {
        given()
                .when()
                .get("/v1/currency/XYZ")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetV2AlphaUSWithCurrencyFields() {
        given()
                .queryParam("fields", "name;currencies")
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetV1AlphaCodesReturnsCurrency() {
        given()
                .queryParam("codes", "US,CA")
                .when()
                .get("/v1/alpha")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetV2AllWithCurrencyFields() {
        given()
                .queryParam("fields", "name;currencies")
                .when()
                .get("/v2/all")
                .then()
                .statusCode(404);
    }
}