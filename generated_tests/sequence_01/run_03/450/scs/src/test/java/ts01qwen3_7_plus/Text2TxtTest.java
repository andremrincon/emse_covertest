package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testWord1Two() {
        given()
            .pathParam("word1", "two")
            .pathParam("word2", "x")
            .pathParam("word3", "y")
        .when()
            .get("/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWord1For() {
        given()
            .pathParam("word1", "for")
            .pathParam("word2", "x")
            .pathParam("word3", "y")
        .when()
            .get("/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200)
            .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testWord1And() {
        given()
            .pathParam("word1", "and")
            .pathParam("word2", "x")
            .pathParam("word3", "y")
        .when()
            .get("/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200)
            .body(equalTo("n"));
    }

    @Test(timeout = 60000)
    public void testWord1Are() {
        given()
            .pathParam("word1", "are")
            .pathParam("word2", "x")
            .pathParam("word3", "y")
        .when()
            .get("/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200)
            .body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testWord1SeeWord2You() {
        given()
            .pathParam("word1", "see")
            .pathParam("word2", "you")
            .pathParam("word3", "y")
        .when()
            .get("/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200)
            .body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testWord1ByWord2TheWord3Way() {
        given()
            .pathParam("word1", "by")
            .pathParam("word2", "the")
            .pathParam("word3", "way")
        .when()
            .get("/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200)
            .body(equalTo("btw"));
    }
}