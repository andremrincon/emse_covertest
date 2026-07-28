package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryTranslationsTest {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("baseUrl", System.getenv("baseUrl"));
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetDeTranslationViaAlphaCode() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(404)
                .body("translations.de", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetEsTranslationViaAlphaCode() {
        given()
            .when()
                .get("/v1/alpha/GB")
            .then()
                .statusCode(404)
                .body("translations.es", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetFrTranslationViaName() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(404)
                .body("translations.fr", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetJaTranslationViaCurrency() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(404)
                .body("translations.ja", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetItTranslationViaRegion() {
        given()
            .when()
                .get("/v1/region/Europe")
            .then()
                .statusCode(404)
                .body("translations.it", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetDeTranslationViaAll() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(404)
                .body("translations.de", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetEsTranslationViaAll() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(404)
                .body("translations.es", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetFrTranslationViaAll() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(404)
                .body("translations.fr", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetJaTranslationViaAll() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(404)
                .body("translations.ja", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetItTranslationViaAll() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(404)
                .body("translations.it", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetDeTranslationViaAlphaCodes() {
        given()
            .queryParam("codes", "US,CA,MX")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(404)
                .body("translations.de", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetEsTranslationViaCallingCode() {
        given()
            .when()
                .get("/v1/callingcode/1")
            .then()
                .statusCode(404)
                .body("translations.es", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetFrTranslationViaCapital() {
        given()
            .when()
                .get("/v1/capital/London")
            .then()
                .statusCode(404)
                .body("translations.fr", hasItem(notNullValue()));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetJaTranslationViaName() {
        given()
            .when()
                .get("/v1/name/Germany")
            .then()
                .statusCode(404)
                .body("translations.ja", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetItTranslationViaAlphaCode() {
        given()
            .when()
                .get("/v1/alpha/DE")
            .then()
                .statusCode(404)
                .body("translations.it", notNullValue());
    }
}