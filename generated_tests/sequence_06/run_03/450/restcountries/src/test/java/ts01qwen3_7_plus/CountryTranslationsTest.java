package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class CountryTranslationsTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSetDe() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .body("translations.de", notNullValue());
    }

    @Test(timeout = 60000)
    public void testSetEs() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .body("translations.es", notNullValue());
    }

    @Test(timeout = 60000)
    public void testSetFr() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .body("translations.fr", notNullValue());
    }

    @Test(timeout = 60000)
    public void testSetJa() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .body("translations.ja", notNullValue());
    }

    @Test(timeout = 60000)
    public void testSetIt() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .body("translations.it", notNullValue());
    }
}