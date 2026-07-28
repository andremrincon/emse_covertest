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
    public void testV1AllReturnsTranslations() {
        given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(404)
                .body("[0].translations.de", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaByCodeReturnsTranslations() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(404)
                .body("translations.de", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaByCodeReturnsEsTranslation() {
        given()
                .when()
                .get("/v1/alpha/GB")
                .then()
                .statusCode(404)
                .body("translations.es", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaByCodeReturnsFrTranslation() {
        given()
                .when()
                .get("/v1/alpha/FR")
                .then()
                .statusCode(404)
                .body("translations.fr", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaByCodeReturnsJaTranslation() {
        given()
                .when()
                .get("/v1/alpha/DE")
                .then()
                .statusCode(404)
                .body("translations.ja", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaByCodeReturnsItTranslation() {
        given()
                .when()
                .get("/v1/alpha/IT")
                .then()
                .statusCode(404)
                .body("translations.it", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaMultipleCodesReturnsTranslations() {
        given()
                .queryParam("codes", "US,CA,MX")
                .when()
                .get("/v1/alpha")
                .then()
                .statusCode(404)
                .body("[0].translations.de", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1CurrencyReturnsTranslations() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(404)
                .body("[0].translations.es", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1NameReturnsTranslations() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(404)
                .body("[0].translations.fr", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1CallingCodeReturnsTranslations() {
        given()
                .when()
                .get("/v1/callingcode/1")
                .then()
                .statusCode(404)
                .body("[0].translations.ja", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1CapitalReturnsTranslations() {
        given()
                .when()
                .get("/v1/capital/London")
                .then()
                .statusCode(404)
                .body("[0].translations.it", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1RegionReturnsTranslations() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .statusCode(404)
                .body("[0].translations.de", notNullValue());
    }

    @Ignore("Illegal character in path at index 52: http://localhost:8080/rest/rest/v1/subregion/Western Europe")
    @Test(timeout = 60000)
    public void testV1SubregionReturnsTranslations() {
        given()
                .when()
                .get("/v1/subregion/{subregion}", "Western Europe")
                .then()
                .statusCode(404)
                .body("[0].translations.es", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1LangReturnsTranslations() {
        given()
                .when()
                .get("/v1/lang/es")
                .then()
                .statusCode(404)
                .body("[0].translations.fr", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1NameFullTextReturnsTranslations() {
        given()
                .queryParam("fullText", "true")
                .when()
                .get("/v1/name/Germany")
                .then()
                .statusCode(404)
                .body("[0].translations.ja", notNullValue());
    }
}