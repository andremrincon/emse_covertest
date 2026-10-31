package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testText2TxtAnd() {
        given()
            .pathParam("word1", "and")
            .pathParam("word2", "quick")
            .pathParam("word3", "brown")
        .when()
            .get("/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .body(equalTo("n"));
    }

    @Test(timeout = 60000)
    public void testText2TxtSeeYou() {
        given()
            .pathParam("word1", "see")
            .pathParam("word2", "you")
            .pathParam("word3", "brown")
        .when()
            .get("/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testText2TxtSeeNotYou() {
        given()
            .pathParam("word1", "see")
            .pathParam("word2", "quick")
            .pathParam("word3", "brown")
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
            .body(equalTo("btw"));
    }

    @Test(timeout = 60000)
    public void testText2TxtByTheNotWay() {
        given()
            .pathParam("word1", "by")
            .pathParam("word2", "the")
            .pathParam("word3", "quick")
        .when()
            .get("/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtByNotTheWay() {
        given()
            .pathParam("word1", "by")
            .pathParam("word2", "quick")
            .pathParam("word3", "way")
        .when()
            .get("/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200);
    }
}