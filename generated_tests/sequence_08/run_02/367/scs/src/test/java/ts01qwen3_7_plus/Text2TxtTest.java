package ts01qwen3_7_plus;

import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class Text2TxtTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null) {
            baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        }
        io.restassured.RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testWord1Two() {
        given()
            .when()
                .get("/api/text2txt/two/word2/word3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWord1For() {
        given()
            .when()
                .get("/api/text2txt/for/word2/word3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWord1Four() {
        given()
            .when()
                .get("/api/text2txt/four/word2/word3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWord1You() {
        given()
            .when()
                .get("/api/text2txt/you/word2/word3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWord1Are() {
        given()
            .when()
                .get("/api/text2txt/are/word2/word3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWord1SeeWord2You() {
        given()
            .when()
                .get("/api/text2txt/see/you/word3")
            .then()
                .statusCode(200);
    }
}