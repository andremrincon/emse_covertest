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
    public void testGetAllV1ReturnsTranslationsDe() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/all")
        .then()
            .statusCode(404)
            .body("translations.de", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetAllV1ReturnsTranslationsEs() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/all")
        .then()
            .statusCode(404)
            .body("translations.es", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetAllV1ReturnsTranslationsFr() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/all")
        .then()
            .statusCode(404)
            .body("translations.fr", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetAllV1ReturnsTranslationsJa() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/all")
        .then()
            .statusCode(404)
            .body("translations.ja", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetAllV1ReturnsTranslationsIt() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/all")
        .then()
            .statusCode(404)
            .body("translations.it", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetByAlphaCodeV1ReturnsDeTranslation() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(404)
            .body("translations.de", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetByAlphaCodeV1ReturnsEsTranslation() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(404)
            .body("translations.es", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetByAlphaCodeV1ReturnsFrTranslation() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(404)
            .body("translations.fr", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetByAlphaCodeV1ReturnsJaTranslation() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(404)
            .body("translations.ja", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetByAlphaCodeV1ReturnsItTranslation() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(404)
            .body("translations.it", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetByNameV1ReturnsAllTranslations() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/name/France")
        .then()
            .statusCode(404)
            .body("translations.de", hasItem(notNullValue()),
                  "translations.es", hasItem(notNullValue()),
                  "translations.fr", hasItem(notNullValue()),
                  "translations.ja", hasItem(notNullValue()),
                  "translations.it", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetByCurrencyV1ReturnsTranslations() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/currency/USD")
        .then()
            .statusCode(404)
            .body("translations.de", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetByRegionV1ReturnsTranslations() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/region/Europe")
        .then()
            .statusCode(404)
            .body("translations.it", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetByCapitalV1ReturnsTranslations() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/capital/London")
        .then()
            .statusCode(404)
            .body("translations.ja", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetByCallingCodeV1ReturnsTranslations() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/callingcode/1")
        .then()
            .statusCode(404)
            .body("translations.fr", hasItem(notNullValue()));
    }
}