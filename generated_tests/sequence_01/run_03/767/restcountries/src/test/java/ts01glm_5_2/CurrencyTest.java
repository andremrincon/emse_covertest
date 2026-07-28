package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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
    public void testGetCountryByCurrencyCodeUSD() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(200)
                .body("[0].currencies", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeUSReturnsCurrencies() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .body("currencies", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetCountryByNameFranceReturnsCurrencies() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(200)
                .body("[0].currencies", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetV2CountryByCurrencyEUR() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/v2/currency/EUR")
                .then()
                .statusCode(200)
                .body("[0].currencies", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetV2AlphaCodeWithCurrenciesFields() {
        given()
                .accept(ContentType.JSON)
                .queryParam("fields", "name;currencies")
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(200)
                .body("currencies", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetV2RegionalblocEUWithCurrenciesFields() {
        given()
                .accept(ContentType.JSON)
                .queryParam("fields", "name;capital;currencies")
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(200)
                .body("[0].currencies", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetV1AllReturnsCurrencies() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/v1/all")
                .then()
                .statusCode(200)
                .body("[0].currencies", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetV2CapitalLondonReturnsCurrencies() {
        given()
                .accept(ContentType.JSON)
                .queryParam("fields", "name;capital;currencies")
                .when()
                .get("/v2/capital/London")
                .then()
                .statusCode(200)
                .body("[0].currencies", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetV1CallingCode1ReturnsCurrencies() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/v1/callingcode/1")
                .then()
                .statusCode(200)
                .body("[0].currencies", notNullValue());
    }
}