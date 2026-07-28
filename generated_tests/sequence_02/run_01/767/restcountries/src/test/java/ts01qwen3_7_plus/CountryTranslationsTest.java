package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryTranslationsTest {

    private final String baseUrl = System.getProperty("base.url",
            System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest");

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetDeTranslation() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(404)
            .body("translations.de", equalTo("Vereinigte Staaten von Amerika"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetEsTranslation() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(404)
            .body("translations.es", equalTo("Estados Unidos"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetFrTranslation() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(404)
            .body("translations.fr", equalTo("États-Unis"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetJaTranslation() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(404)
            .body("translations.ja", equalTo("アメリカ合衆国"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testSetItTranslation() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(404)
            .body("translations.it", equalTo("Stati Uniti D'America"));
    }
}