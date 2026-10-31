package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testWord1Two() {
        RestAssured.given()
            .when()
            .get("/api/text2txt/two/x/y")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWord1For() {
        RestAssured.given()
            .when()
            .get("/api/text2txt/for/x/y")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWord1Four() {
        RestAssured.given()
            .when()
            .get("/api/text2txt/four/x/y")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWord1You() {
        RestAssured.given()
            .when()
            .get("/api/text2txt/you/x/y")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWord1And() {
        RestAssured.given()
            .when()
            .get("/api/text2txt/and/x/y")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWord1Are() {
        RestAssured.given()
            .when()
            .get("/api/text2txt/are/x/y")
            .then()
            .statusCode(200);
    }
}