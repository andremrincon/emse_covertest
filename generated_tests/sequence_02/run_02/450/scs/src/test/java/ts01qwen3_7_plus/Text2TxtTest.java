package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class Text2TxtTest {

    @Test(timeout = 60000)
    public void testText2TxtTwo() {
        given()
            .pathParam("word1", "two")
            .pathParam("word2", "x")
            .pathParam("word3", "y")
        .when()
            .get("http://localhost:8080/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtFor() {
        given()
            .pathParam("word1", "for")
            .pathParam("word2", "x")
            .pathParam("word3", "y")
        .when()
            .get("http://localhost:8080/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtYou() {
        given()
            .pathParam("word1", "you")
            .pathParam("word2", "x")
            .pathParam("word3", "y")
        .when()
            .get("http://localhost:8080/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtAnd() {
        given()
            .pathParam("word1", "and")
            .pathParam("word2", "x")
            .pathParam("word3", "y")
        .when()
            .get("http://localhost:8080/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtAre() {
        given()
            .pathParam("word1", "are")
            .pathParam("word2", "x")
            .pathParam("word3", "y")
        .when()
            .get("http://localhost:8080/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtSeeYou() {
        given()
            .pathParam("word1", "see")
            .pathParam("word2", "you")
            .pathParam("word3", "y")
        .when()
            .get("http://localhost:8080/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200);
    }
}