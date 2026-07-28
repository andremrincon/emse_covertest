package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

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
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void testSetDeViaV1AlphaCode() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200)
            .body("translations.de", notNullValue());
    }

    @Test(timeout = 60000)
    public void testSetEsViaV1AlphaCode() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200)
            .body("translations.es", notNullValue());
    }

    @Test(timeout = 60000)
    public void testSetFrViaV1AlphaCode() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200)
            .body("translations.fr", notNullValue());
    }

    @Test(timeout = 60000)
    public void testSetJaViaV1AlphaCode() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200)
            .body("translations.ja", notNullValue());
    }

    @Test(timeout = 60000)
    public void testSetItViaV1AlphaCode() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200)
            .body("translations.it", notNullValue());
    }

    @Test(timeout = 60000)
    public void testSetDeViaV1Name() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/name/France")
        .then()
            .statusCode(200)
            .body("translations.de", notNullValue());
    }

    @Test(timeout = 60000)
    public void testSetEsViaV1Name() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/name/France")
        .then()
            .statusCode(200)
            .body("translations.es", notNullValue());
    }

    @Test(timeout = 60000)
    public void testSetFrViaV1Name() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/name/France")
        .then()
            .statusCode(200)
            .body("translations.fr", notNullValue());
    }

    @Test(timeout = 60000)
    public void testSetJaViaV1Name() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/name/France")
        .then()
            .statusCode(200)
            .body("translations.ja", notNullValue());
    }

    @Test(timeout = 60000)
    public void testSetItViaV1Name() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/name/France")
        .then()
            .statusCode(200)
            .body("translations.it", notNullValue());
    }

    @Test(timeout = 60000)
    public void testSetDeViaV1Currency() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/currency/USD")
        .then()
            .statusCode(200)
            .body("translations.de", hasItem(notNullValue()));
    }

    @Test(timeout = 60000)
    public void testSetEsViaV1Region() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/region/Europe")
        .then()
            .statusCode(200)
            .body("translations.es", hasItem(notNullValue()));
    }

    @Test(timeout = 60000)
    public void testSetFrViaV1Capital() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/capital/London")
        .then()
            .statusCode(200)
            .body("translations.fr", hasItem(notNullValue()));
    }

    @Test(timeout = 60000)
    public void testSetJaViaV1CallingCode() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/v1/callingcode/1")
        .then()
            .statusCode(200)
            .body("translations.ja", hasItem(notNullValue()));
    }

    @Test(timeout = 60000)
    public void testSetItViaV1AlphaCodes() {
        given()
            .accept(ContentType.JSON)
            .queryParam("codes", "US,CA,MX")
        .when()
            .get("/v1/alpha")
        .then()
            .statusCode(400);
    }
}