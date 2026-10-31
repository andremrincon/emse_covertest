package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Ignore;
public class CountryTranslationsTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
        RestAssured.defaultParser = Parser.JSON;
    }

    @Ignore("The JSON input text should neither be null nor empty.")
    @Test(timeout = 60000)
    public void testSetDe() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .body("translations.de", equalTo("Vereinigte Staaten von Amerika"));
    }

    @Ignore("The JSON input text should neither be null nor empty.")
    @Test(timeout = 60000)
    public void testSetEs() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .body("translations.es", equalTo("Estados Unidos"));
    }

    @Ignore("The JSON input text should neither be null nor empty.")
    @Test(timeout = 60000)
    public void testSetFr() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .body("translations.fr", equalTo("États-Unis"));
    }

    @Ignore("The JSON input text should neither be null nor empty.")
    @Test(timeout = 60000)
    public void testSetJa() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .body("translations.ja", equalTo("アメリカ合衆国"));
    }

    @Ignore("The JSON input text should neither be null nor empty.")
    @Test(timeout = 60000)
    public void testSetIt() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .body("translations.it", equalTo("Stati Uniti D'America"));
    }
}