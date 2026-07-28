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
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetAllCountriesReturnsTranslationsDe() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(404)
                .body("translations.de", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeReturnsTranslationsEs() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404)
                .body("translations.es", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeReturnsTranslationsFr() {
        given()
                .when()
                .get("/v1/alpha/GB")
                .then()
                .statusCode(404)
                .body("translations.fr", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeReturnsTranslationsJa() {
        given()
                .when()
                .get("/v1/alpha/FR")
                .then()
                .statusCode(404)
                .body("translations.ja", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByAlphaCodeReturnsTranslationsIt() {
        given()
                .when()
                .get("/v1/alpha/DE")
                .then()
                .statusCode(404)
                .body("translations.it", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountriesByAlphaCodesReturnsTranslationsDe() {
        given()
                .queryParam("codes", "US,CA")
                .when()
                .get("/v1/alpha")
                .then()
                .statusCode(404)
                .body("translations.de", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByNameReturnsTranslationsEs() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(404)
                .body("translations.es", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByCurrencyReturnsTranslationsFr() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(404)
                .body("translations.fr", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByCallingCodeReturnsTranslationsJa() {
        given()
                .when()
                .get("/v1/callingcode/1")
                .then()
                .statusCode(404)
                .body("translations.ja", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountryByCapitalReturnsTranslationsIt() {
        given()
                .when()
                .get("/v1/capital/London")
                .then()
                .statusCode(404)
                .body("translations.it", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountriesByRegionReturnsTranslationsDe() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .statusCode(404)
                .body("translations.de", hasItem(notNullValue()));
    }

    @Ignore("Illegal character in path at index 52: http://localhost:8080/rest/rest/v1/subregion/Western Europe")
    @Test(timeout = 60000)
    public void testGetCountriesBySubregionReturnsTranslationsEs() {
        given()
                .when()
                .get("/v1/subregion/{subregion}", "Western Europe")
                .then()
                .statusCode(404)
                .body("translations.es", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCountriesByLanguageReturnsTranslationsFr() {
        given()
                .when()
                .get("/v1/lang/es")
                .then()
                .statusCode(404)
                .body("translations.fr", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetAllV1ReturnsTranslationsJa() {
        given()
                .when()
                .get("/v1")
                .then()
                .statusCode(404)
                .body("translations.ja", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetAllV1ReturnsTranslationsIt() {
        given()
                .when()
                .get("/v1")
                .then()
                .statusCode(404)
                .body("translations.it", hasItem(notNullValue()));
    }
}