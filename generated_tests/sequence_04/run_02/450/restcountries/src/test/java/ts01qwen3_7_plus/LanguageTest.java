package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class LanguageTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("test.base.uri", "http://localhost:8080");
        RestAssured.basePath = System.getProperty("test.base.path", "/rest");
    }

    @Test(timeout = 60000)
    public void testSetLanguageFieldsViaAlphaCode() {
        given()
            .pathParam("alphacode", "US")
        .when()
            .get("/v1/alpha/{alphacode}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSetLanguageFieldsViaName() {
        given()
            .pathParam("name", "France")
        .when()
            .get("/v1/name/{name}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSetLanguageFieldsViaCurrency() {
        given()
            .pathParam("currency", "USD")
        .when()
            .get("/v1/currency/{currency}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSetLanguageFieldsViaLang() {
        given()
            .pathParam("lang", "es")
        .when()
            .get("/v1/lang/{lang}")
        .then()
            .statusCode(200);
    }
}