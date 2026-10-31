package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class CountryTranslationsTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("BASE_URL", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testSetDe() {
        given()
            .pathParam("alphacode", "US")
        .when()
            .get("/v1/alpha/{alphacode}")
        .then()
            .body("translations.de", notNullValue());
    }

    @Test(timeout = 60000)
    public void testSetEs() {
        given()
            .pathParam("alphacode", "US")
        .when()
            .get("/v1/alpha/{alphacode}")
        .then()
            .body("translations.es", notNullValue());
    }

    @Test(timeout = 60000)
    public void testSetFr() {
        given()
            .pathParam("alphacode", "US")
        .when()
            .get("/v1/alpha/{alphacode}")
        .then()
            .body("translations.fr", notNullValue());
    }

    @Test(timeout = 60000)
    public void testSetJa() {
        given()
            .pathParam("alphacode", "US")
        .when()
            .get("/v1/alpha/{alphacode}")
        .then()
            .body("translations.ja", notNullValue());
    }

    @Test(timeout = 60000)
    public void testSetIt() {
        given()
            .pathParam("alphacode", "US")
        .when()
            .get("/v1/alpha/{alphacode}")
        .then()
            .body("translations.it", notNullValue());
    }
}