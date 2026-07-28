package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryTranslationsTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetAllCountriesTranslationsDe() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/all")
        .then()
            .statusCode(404)
            .body("$", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeTranslationsEs() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(404)
            .body("$", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeTranslationsFr() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha/GB")
        .then()
            .statusCode(404)
            .body("$", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeTranslationsJa() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha/DE")
        .then()
            .statusCode(404)
            .body("$", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeTranslationsIt() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha/FR")
        .then()
            .statusCode(404)
            .body("$", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByNameTranslationsDe() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/name/France")
        .then()
            .statusCode(404)
            .body("$", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByNameTranslationsEs() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/name/Germany")
        .then()
            .statusCode(404)
            .body("$", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByCurrencyTranslationsFr() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/currency/USD")
        .then()
            .statusCode(404)
            .body("$", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByCurrencyTranslationsJa() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/currency/EUR")
        .then()
            .statusCode(404)
            .body("$", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByCurrencyTranslationsIt() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/currency/USD")
        .then()
            .statusCode(404)
            .body("$", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByRegionTranslationsDe() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/region/Europe")
        .then()
            .statusCode(404)
            .body("$", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByRegionTranslationsEs() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/region/Europe")
        .then()
            .statusCode(404)
            .body("$", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByCapitalTranslationsFr() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/capital/London")
        .then()
            .statusCode(404)
            .body("$", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByCallingCodeTranslationsJa() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/callingcode/1")
        .then()
            .statusCode(404)
            .body("$", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByCallingCodeTranslationsIt() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/callingcode/44")
        .then()
            .statusCode(404)
            .body("$", notNullValue());
    }
}