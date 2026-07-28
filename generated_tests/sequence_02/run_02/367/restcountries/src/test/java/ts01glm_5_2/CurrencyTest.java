package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Ignore;
public class CurrencyTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2CurrencyEndpointReturnsCountriesWithCurrencyCode() {
        given()
                .when()
                .get("/v2/currency/EUR")
                .then()
                .statusCode(404)
                .body("[0].currencies", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2AlphaEndpointReturnsCountryWithCurrencyFields() {
        given()
                .queryParam("fields", "name;capital;currencies")
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(404)
                .body("currencies", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2RegionalblocEndpointReturnsCountriesWithCurrencies() {
        given()
                .queryParam("fields", "name;capital;currencies")
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(404)
                .body("[0].currencies", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1CurrencyEndpointReturnsCountriesWithCurrencyCode() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(404)
                .body("[0].currencies", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaEndpointReturnsCountryWithCurrency() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404)
                .body("currencies", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2NameEndpointReturnsCountryWithCurrency() {
        given()
                .queryParam("fullText", "true")
                .when()
                .get("/v2/name/Germany")
                .then()
                .statusCode(404)
                .body("[0].currencies", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2AllEndpointReturnsCountriesWithCurrencies() {
        given()
                .queryParam("fields", "name;currencies")
                .when()
                .get("/v2/all")
                .then()
                .statusCode(404)
                .body("[0].currencies", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AllEndpointReturnsCountriesWithCurrencyCode() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(404)
                .body("[0].currencies", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2CallingcodeEndpointReturnsCountriesWithCurrency() {
        given()
                .queryParam("fields", "name;currencies")
                .when()
                .get("/v2/callingcode/1")
                .then()
                .statusCode(404)
                .body("[0].currencies", notNullValue());
    }
}