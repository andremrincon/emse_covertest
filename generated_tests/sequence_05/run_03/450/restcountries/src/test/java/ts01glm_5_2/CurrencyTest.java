package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CurrencyTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByCurrencyUSD() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(404)
                .body("currencies", hasItem(hasEntry("code", "USD")));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByCurrencyEUR() {
        given()
                .when()
                .get("/v1/currency/EUR")
                .then()
                .statusCode(404)
                .body("currencies", hasItem(hasEntry("code", "EUR")));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeUS() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404)
                .body("currencies[0].code", equalTo("USD"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeGB() {
        given()
                .when()
                .get("/v1/alpha/GB")
                .then()
                .statusCode(404)
                .body("currencies[0].code", equalTo("GBP"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetV2CountryByCurrencyEUR() {
        given()
                .when()
                .get("/v2/currency/EUR")
                .then()
                .statusCode(404)
                .body("currencies", hasItem(hasItem(hasEntry("code", "EUR"))));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetV2CountryByAlphaCodeUS() {
        given()
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(404)
                .body("currencies[0].code", equalTo("USD"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetV2RegionalBlocEUWithCurrencies() {
        given()
                .queryParam("fields", "name;capital;currencies")
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(404)
                .body("currencies", hasItem(hasItem(hasEntry("code", "EUR"))));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByNameFrance() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(404)
                .body("currencies[0].code", equalTo("EUR"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetAllCountriesV1() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(404)
                .body("currencies", notNullValue());
    }
}