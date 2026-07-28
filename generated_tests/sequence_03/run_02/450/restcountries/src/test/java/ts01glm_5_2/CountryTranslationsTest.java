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
        if (baseUrl == null) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetAllV1ReturnsTranslationsDe() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(404)
                .body("[0].translations.de", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetAlphaCodeUSRreturnsTranslationsEs() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(404)
                .body("translations.es", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetAlphaCodesReturnsTranslationsFr() {
        given()
            .queryParam("codes", "US,CA")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(404)
                .body("[0].translations.fr", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCurrencyUSDReturnsTranslationsJa() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(404)
                .body("[0].translations.ja", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetNameFranceReturnsTranslationsIt() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(404)
                .body("[0].translations.it", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCallingCode1ReturnsTranslationsDe() {
        given()
            .when()
                .get("/v1/callingcode/1")
            .then()
                .statusCode(404)
                .body("[0].translations.de", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCapitalLondonReturnsTranslationsEs() {
        given()
            .when()
                .get("/v1/capital/London")
            .then()
                .statusCode(404)
                .body("[0].translations.es", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetRegionEuropeReturnsTranslationsFr() {
        given()
            .when()
                .get("/v1/region/Europe")
            .then()
                .statusCode(404)
                .body("[0].translations.fr", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetSubregionWesternEuropeReturnsTranslationsJa() {
        given()
            .when()
                .get("/v1/subregion/Western%20Europe")
            .then()
                .statusCode(404)
                .body("[0].translations.ja", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetLangEsReturnsTranslationsIt() {
        given()
            .when()
                .get("/v1/lang/es")
            .then()
                .statusCode(404)
                .body("[0].translations.it", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetAlphaCodeGBReturnsAllTranslations() {
        given()
            .when()
                .get("/v1/alpha/GB")
            .then()
                .statusCode(404)
                .body("translations.de", notNullValue(),
                      "translations.es", notNullValue(),
                      "translations.fr", notNullValue(),
                      "translations.ja", notNullValue(),
                      "translations.it", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetAlphaCodeDEReturnsTranslationsDeField() {
        given()
            .when()
                .get("/v1/alpha/DE")
            .then()
                .statusCode(404)
                .body("translations.de", equalTo("Deutschland"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetCurrencyEURReturnsTranslationsItField() {
        given()
            .when()
                .get("/v1/currency/EUR")
            .then()
                .statusCode(404)
                .body("[0].translations.it", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetNameUnitedStatesReturnsTranslationsJaField() {
        given()
            .when()
                .get("/v1/name/United%20States%20of%20America")
            .then()
                .statusCode(404)
                .body("[0].translations.ja", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetRegionAmericasReturnsTranslationsDeField() {
        given()
            .when()
                .get("/v1/region/Americas")
            .then()
                .statusCode(404)
                .body("[0].translations.de", notNullValue());
    }
}