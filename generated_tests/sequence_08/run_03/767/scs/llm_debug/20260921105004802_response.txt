package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseURI", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testText2TxtDefault() {
        given()
            .pathParam("word1", "The")
            .pathParam("word2", "quick")
            .pathParam("word3", "brown")
        .when()
            .get("/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtTwo() {
        given()
            .pathParam("word1", "two")
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
            .statusCode(200);
    }
}