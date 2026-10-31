package ts01qwen3_7_plus_3;

import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class CountryTranslationsTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testSetDe() {
        given()
            .when()
                .get(baseUrl + "/v1/alpha/US")
            .then()
                .body("translations.de", notNullValue());
    }

    @Test(timeout = 60000)
    public void testSetEs() {
        given()
            .when()
                .get(baseUrl + "/v1/alpha/US")
            .then()
                .body("translations.es", notNullValue());
    }

    @Test(timeout = 60000)
    public void testSetFr() {
        given()
            .when()
                .get(baseUrl + "/v1/alpha/US")
            .then()
                .body("translations.fr", notNullValue());
    }

    @Test(timeout = 60000)
    public void testSetJa() {
        given()
            .when()
                .get(baseUrl + "/v1/alpha/US")
            .then()
                .body("translations.ja", notNullValue());
    }

    @Test(timeout = 60000)
    public void testSetIt() {
        given()
            .when()
                .get(baseUrl + "/v1/alpha/US")
            .then()
                .body("translations.it", notNullValue());
    }
}