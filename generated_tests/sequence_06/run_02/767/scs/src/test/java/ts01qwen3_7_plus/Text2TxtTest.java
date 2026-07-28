package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class Text2TxtTest {

    @Test(timeout = 60000)
    public void testText2TxtFor() {
        given()
            .pathParam("word1", "for")
            .pathParam("word2", "a")
            .pathParam("word3", "b")
        .when()
            .get("http://localhost:8080/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtFour() {
        given()
            .pathParam("word1", "four")
            .pathParam("word2", "a")
            .pathParam("word3", "b")
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
            .pathParam("word3", "b")
        .when()
            .get("http://localhost:8080/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtSeeOther() {
        given()
            .pathParam("word1", "see")
            .pathParam("word2", "a")
            .pathParam("word3", "b")
        .when()
            .get("http://localhost:8080/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtByTheWay() {
        given()
            .pathParam("word1", "by")
            .pathParam("word2", "the")
            .pathParam("word3", "way")
        .when()
            .get("http://localhost:8080/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtNoMatch() {
        given()
            .pathParam("word1", "a")
            .pathParam("word2", "b")
            .pathParam("word3", "c")
        .when()
            .get("http://localhost:8080/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200);
    }
}