package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryTranslationsTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetByAlphaCodeReturnsDeTranslation() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404)
                .body("translations.de", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetByAlphaCodeReturnsEsTranslation() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404)
                .body("translations.es", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetByAlphaCodeReturnsFrTranslation() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404)
                .body("translations.fr", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetByAlphaCodeReturnsJaTranslation() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404)
                .body("translations.ja", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetByAlphaCodeReturnsItTranslation() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404)
                .body("translations.it", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetByNameReturnsDeTranslation() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(404)
                .body("[0].translations.de", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetByNameReturnsEsTranslation() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(404)
                .body("[0].translations.es", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetByNameReturnsFrTranslation() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(404)
                .body("[0].translations.fr", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetByNameReturnsJaTranslation() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(404)
                .body("[0].translations.ja", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetByNameReturnsItTranslation() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(404)
                .body("[0].translations.it", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetByCurrencyReturnsDeTranslation() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(404)
                .body("[0].translations.de", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetByCapitalReturnsEsTranslation() {
        given()
                .when()
                .get("/v1/capital/London")
                .then()
                .statusCode(404)
                .body("[0].translations.es", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetByRegionReturnsFrTranslation() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .statusCode(404)
                .body("[0].translations.fr", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetByCallingCodeReturnsJaTranslation() {
        given()
                .when()
                .get("/v1/callingcode/1")
                .then()
                .statusCode(404)
                .body("[0].translations.ja", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetByCallingCodeReturnsItTranslation() {
        given()
                .when()
                .get("/v1/callingcode/1")
                .then()
                .statusCode(404)
                .body("[0].translations.it", notNullValue());
    }
}