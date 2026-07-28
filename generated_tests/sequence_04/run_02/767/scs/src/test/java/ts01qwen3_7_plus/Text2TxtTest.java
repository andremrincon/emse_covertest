package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class Text2TxtTest {

    static {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testText2TxtSeeYou() {
        given()
            .pathParam("word1", "see")
            .pathParam("word2", "you")
            .pathParam("word3", "later")
        .when()
            .get("/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtSeeNotYou() {
        given()
            .pathParam("word1", "see")
            .pathParam("word2", "me")
            .pathParam("word3", "later")
        .when()
            .get("/api/text2txt/{word1}/{word2}/{word3}")
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
            .get("/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtByTheNotWay() {
        given()
            .pathParam("word1", "by")
            .pathParam("word2", "the")
            .pathParam("word3", "bus")
        .when()
            .get("/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtByNotTheWay() {
        given()
            .pathParam("word1", "by")
            .pathParam("word2", "me")
            .pathParam("word3", "way")
        .when()
            .get("/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtDefault() {
        given()
            .pathParam("word1", "hello")
            .pathParam("word2", "world")
            .pathParam("word3", "foo")
        .when()
            .get("/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200);
    }
}