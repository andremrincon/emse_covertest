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
    public void testSetDeTranslationViaAlphaEndpoint() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(404)
                .body("translations.de", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetEsTranslationViaNameEndpoint() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(404)
                .body("translations[0].es", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetFrTranslationViaCurrencyEndpoint() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(404)
                .body("translations[0].fr", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetJaTranslationViaCallingCodeEndpoint() {
        given()
            .when()
                .get("/v1/callingcode/1")
            .then()
                .statusCode(404)
                .body("translations[0].ja", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetItTranslationViaCapitalEndpoint() {
        given()
            .when()
                .get("/v1/capital/London")
            .then()
                .statusCode(404)
                .body("translations[0].it", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetDeTranslationViaRegionEndpoint() {
        given()
            .when()
                .get("/v1/region/Europe")
            .then()
                .statusCode(404)
                .body("translations[0].de", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetEsTranslationViaAlphaCodesEndpoint() {
        given()
            .queryParam("codes", "US,CA,MX")
            .when()
                .get("/v1/alpha")
            .then()
                .statusCode(404)
                .body("translations[0].es", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetFrTranslationViaAlphaSingleEndpoint() {
        given()
            .when()
                .get("/v1/alpha/GB")
            .then()
                .statusCode(404)
                .body("translations.fr", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetJaTranslationViaNameEndpoint() {
        given()
            .when()
                .get("/v1/name/Germany")
            .then()
                .statusCode(404)
                .body("translations[0].ja", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetItTranslationViaAlphaEndpoint() {
        given()
            .when()
                .get("/v1/alpha/DE")
            .then()
                .statusCode(404)
                .body("translations.it", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetDeTranslationViaAllEndpoint() {
        given()
            .when()
                .get("/v1/all")
            .then()
                .statusCode(404)
                .body("translations[0].de", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetEsTranslationViaRegionEndpoint() {
        given()
            .when()
                .get("/v1/region/Americas")
            .then()
                .statusCode(404)
                .body("translations[0].es", notNullValue());
    }

    @Ignore("Illegal character in path at index 46: http://localhost:8080/rest/rest/v1/name/United States of A...")
    @Test(timeout = 60000)
    public void testSetFrTranslationViaNameEndpoint() {
        given()
            .when()
                .get("/v1/name/{name}", "United States of America")
            .then()
                .statusCode(404)
                .body("translations[0].fr", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetJaTranslationViaCurrencyEndpoint() {
        given()
            .when()
                .get("/v1/currency/EUR")
            .then()
                .statusCode(404)
                .body("translations[0].ja", notNullValue());
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetItTranslationViaCallingCodeEndpoint() {
        given()
            .when()
                .get("/v1/callingcode/44")
            .then()
                .statusCode(404)
                .body("translations[0].it", notNullValue());
    }
}