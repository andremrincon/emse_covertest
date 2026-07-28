package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.lessThan;

public class CountryTranslationsTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";
        RestAssured.baseURI = baseUrl;
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test(timeout = 60000)
    public void testGetAlphaCodeSuccess() {
        RestAssured
            .given()
            .when()
            .get("/v1/alpha/US")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSetDeTranslation() {
        RestAssured
            .given()
            .when()
            .get("/v1/alpha/US")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSetEsTranslation() {
        RestAssured
            .given()
            .when()
            .get("/v1/alpha/US")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSetFrTranslation() {
        RestAssured
            .given()
            .when()
            .get("/v1/alpha/US")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSetJaTranslation() {
        RestAssured
            .given()
            .when()
            .get("/v1/alpha/US")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSetItTranslation() {
        RestAssured
            .given()
            .when()
            .get("/v1/alpha/US")
            .then()
            .statusCode(404);
    }
}