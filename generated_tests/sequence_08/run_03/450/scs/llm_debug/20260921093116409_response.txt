package ts01qwen3_7_plus_3;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @Test(timeout = 60000)
    public void testSeeYou() {
        given()
            .pathParam("word1", "see")
            .pathParam("word2", "you")
            .pathParam("word3", "a")
        .when()
            .get("/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200)
            .body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWay() {
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

    @Test(timeout = 60000)
    public void testTwo() {
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
    public void testFor() {
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
    public void testYou() {
        given()
            .pathParam("word1", "you")
            .pathParam("word2", "x")
            .pathParam("word3", "y")
        .when()
            .get("/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200)
            .body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testAnd() {
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
}