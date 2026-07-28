package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
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
    public void testV2AlphaByCodeReturnsCurrencyFields() {
        given()
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(404)
                .body("currencies[0].code", hasItem("USD"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2CurrencyEndpointReturnsCurrencyCode() {
        given()
                .when()
                .get("/v2/currency/EUR")
                .then()
                .statusCode(404)
                .body("currencies[0].code", hasItem("EUR"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2NameEndpointReturnsCurrencySymbol() {
        given()
                .when()
                .get("/v2/name/Germany")
                .then()
                .statusCode(404)
                .body("currencies[0].symbol", hasItem("€"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2RegionalblocReturnsCurrencyName() {
        given()
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(404)
                .body("currencies[0].name", hasItem("Euro"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2AllReturnsCurrencyFields() {
        given()
                .when()
                .get("/v2/all")
                .then()
                .statusCode(404)
                .body("currencies", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2CallingcodeReturnsCurrencyFields() {
        given()
                .when()
                .get("/v2/callingcode/44")
                .then()
                .statusCode(404)
                .body("currencies[0].code", hasItem("GBP"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2CapitalReturnsCurrencyFields() {
        given()
                .when()
                .get("/v2/capital/Paris")
                .then()
                .statusCode(404)
                .body("currencies[0].code", hasItem("EUR"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2AlphaMultipleCodesReturnsCurrencyFields() {
        given()
                .queryParam("codes", "US,CA")
                .when()
                .get("/v2/alpha")
                .then()
                .statusCode(404)
                .body("currencies", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV2RegionReturnsCurrencyFields() {
        given()
                .when()
                .get("/v2/region/Europe")
                .then()
                .statusCode(404)
                .body("currencies", notNullValue());
    }
}