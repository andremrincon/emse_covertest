package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class Text2TxtTest {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testWord1Two() {
        given()
            .pathParam("word1", "two")
            .pathParam("word2", "a")
            .pathParam("word3", "b")
        .when()
            .get(baseUrl + "/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWord1For() {
        given()
            .pathParam("word1", "for")
            .pathParam("word2", "a")
            .pathParam("word3", "b")
        .when()
            .get(baseUrl + "/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWord1And() {
        given()
            .pathParam("word1", "and")
            .pathParam("word2", "a")
            .pathParam("word3", "b")
        .when()
            .get(baseUrl + "/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWord1Are() {
        given()
            .pathParam("word1", "are")
            .pathParam("word2", "a")
            .pathParam("word3", "b")
        .when()
            .get(baseUrl + "/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWord1SeeWord2You() {
        given()
            .pathParam("word1", "see")
            .pathParam("word2", "you")
            .pathParam("word3", "b")
        .when()
            .get(baseUrl + "/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWord1ByWord2TheWord3Way() {
        given()
            .pathParam("word1", "by")
            .pathParam("word2", "the")
            .pathParam("word3", "way")
        .when()
            .get(baseUrl + "/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200);
    }
}