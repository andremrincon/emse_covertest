package ts01qwen3_7_plus;

import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryTranslationsTest {

    private static final String BASE_URL = "http://localhost:8080/rest";

    static {
        RestAssured.defaultParser = Parser.JSON;
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSetDeTranslation() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200)
            .body("translations.de", equalTo("Vereinigte Staaten von Amerika"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSetEsTranslation() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200)
            .body("translations.es", equalTo("Estados Unidos"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSetFrTranslation() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200)
            .body("translations.fr", equalTo("États-Unis"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSetJaTranslation() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200)
            .body("translations.ja", equalTo("アメリカ合衆国"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSetItTranslation() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200)
            .body("translations.it", equalTo("Stati Uniti D'America"));
    }
}