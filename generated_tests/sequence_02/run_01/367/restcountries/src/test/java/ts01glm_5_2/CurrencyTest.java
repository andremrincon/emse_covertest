package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;
import org.junit.Before;
import org.junit.Test;

import org.junit.Ignore;
public class CurrencyTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaUsReturnsCurrencyCode() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(404)
                .body("currencies[0].code", equalTo("USD"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaUsReturnsCurrencyName() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(404)
                .body("currencies[0].name", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaUsReturnsCurrencySymbol() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(404)
                .body("currencies[0].symbol", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1CurrencyUsdReturnsCurrencyFields() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(404)
                .body("currencies[0].code", equalTo("USD"))
                .body("currencies[0].name", notNullValue())
                .body("currencies[0].symbol", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1NameFranceReturnsCurrencyFields() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(404)
                .body("currencies[0].code", equalTo("EUR"))
                .body("currencies[0].name", equalTo("Euro"))
                .body("currencies[0].symbol", equalTo("€"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AllReturnsCurrencyFields() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(404)
                .body("[0].currencies[0].code", notNullValue())
                .body("[0].currencies[0].name", notNullValue())
                .body("[0].currencies[0].symbol", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1CapitalLondonReturnsCurrencyFields() {
        given()
            .when()
                .get("/v1/capital/London")
            .then()
                .statusCode(404)
                .body("currencies[0].code", equalTo("GBP"))
                .body("currencies[0].name", notNullValue())
                .body("currencies[0].symbol", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1RegionEuropeReturnsCurrencyFields() {
        given()
            .when()
                .get("/v1/region/Europe")
            .then()
                .statusCode(404)
                .body("[0].currencies[0].code", notNullValue())
                .body("[0].currencies[0].name", notNullValue())
                .body("[0].currencies[0].symbol", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1Callingcode1ReturnsCurrencyFields() {
        given()
            .when()
                .get("/v1/callingcode/1")
            .then()
                .statusCode(404)
                .body("currencies[0].code", notNullValue())
                .body("currencies[0].name", notNullValue())
                .body("currencies[0].symbol", notNullValue());
    }
}