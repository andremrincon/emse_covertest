package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CountryTranslationsTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.trim().isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testTranslationsViaAlphaCode() {
        given()
            .pathParam("alphacode", "US")
        .when()
            .get("/v1/alpha/{alphacode}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTranslationsViaName() {
        given()
            .pathParam("name", "France")
        .when()
            .get("/v1/name/{name}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTranslationsViaCurrency() {
        given()
            .pathParam("currency", "USD")
        .when()
            .get("/v1/currency/{currency}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTranslationsViaCallingCode() {
        given()
            .pathParam("callingcode", "1")
        .when()
            .get("/v1/callingcode/{callingcode}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTranslationsViaCapital() {
        given()
            .pathParam("capital", "Paris")
        .when()
            .get("/v1/capital/{capital}")
        .then()
            .statusCode(200);
    }
}