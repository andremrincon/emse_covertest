package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CountryTranslationsTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testSetDe() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .body("translations.de", equalTo("Vereinigte Staaten von Amerika"));
    }

    @Test(timeout = 60000)
    public void testSetEs() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .body("translations.es", equalTo("Estados Unidos"));
    }

    @Test(timeout = 60000)
    public void testSetFr() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .body("translations.fr", equalTo("États-Unis"));
    }

    @Test(timeout = 60000)
    public void testSetJa() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .body("translations.ja", equalTo("アメリカ合衆国"));
    }

    @Test(timeout = 60000)
    public void testSetIt() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .body("translations.it", equalTo("Stati Uniti D'America"));
    }
}