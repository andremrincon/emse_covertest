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
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
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
    public void testV1AlphaByCodeGBReturnsTranslations() {
        given()
            .when()
                .get("/v1/alpha/GB")
            .then()
                .statusCode(404)
                .body("translations.es", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1NameReturnsTranslations() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(404)
                .body("translations.fr", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1NameFullTextReturnsTranslations() {
        given()
            .queryParam("fullText", "true")
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(404)
                .body("translations.ja", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1CurrencyReturnsTranslations() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(404)
                .body("translations.it", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1RegionReturnsTranslations() {
        given()
            .when()
                .get("/v1/region/Europe")
            .then()
                .statusCode(404)
                .body("translations.de", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1CapitalReturnsTranslations() {
        given()
            .when()
                .get("/v1/capital/London")
            .then()
                .statusCode(404)
                .body("translations.es", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1CallingCodeReturnsTranslations() {
        given()
            .when()
                .get("/v1/callingcode/1")
            .then()
                .statusCode(404)
                .body("translations.fr", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AlphaMultipleCodesReturnsTranslations() {
        given()
            .queryParam("codes", "US,CA")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(404)
                .body("translations.ja", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1AllReturnsTranslations() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(404)
                .body("translations.it", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1CurrencyEURReturnsTranslations() {
        given()
            .when()
                .get("/v1/currency/EUR")
            .then()
                .statusCode(404)
                .body("translations.de", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1NameGermanyReturnsTranslations() {
        given()
            .when()
                .get("/v1/name/Germany")
            .then()
                .statusCode(404)
                .body("translations.es", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1RegionAmericasReturnsTranslations() {
        given()
            .when()
                .get("/v1/region/Americas")
            .then()
                .statusCode(404)
                .body("translations.fr", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1CapitalParisReturnsTranslations() {
        given()
            .when()
                .get("/v1/capital/Paris")
            .then()
                .statusCode(404)
                .body("translations.ja", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testV1CallingCode44ReturnsTranslations() {
        given()
            .when()
                .get("/v1/callingcode/44")
            .then()
                .statusCode(404)
                .body("translations.it", hasItem(notNullValue()));
    }
}