package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null) {
            baseUrl = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testText2TxtTwo() {
        given()
            .pathParam("word1", "two")
            .pathParam("word2", "x")
            .pathParam("word3", "y")
        .when()
            .get("/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testText2TxtFor() {
        given()
            .pathParam("word1", "for")
            .pathParam("word2", "x")
            .pathParam("word3", "y")
        .when()
            .get("/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testText2TxtFour() {
        given()
            .pathParam("word1", "four")
            .pathParam("word2", "x")
            .pathParam("word3", "y")
        .when()
            .get("/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testText2TxtSeeYou() {
        given()
            .pathParam("word1", "see")
            .pathParam("word2", "you")
            .pathParam("word3", "x")
        .when()
            .get("/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .body(equalTo("cu"));
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
    public void testText2TxtNoMatch() {
        given()
            .pathParam("word1", "hello")
            .pathParam("word2", "world")
            .pathParam("word3", "foo")
        .when()
            .get("/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .body(equalTo(""));
    }
}