package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class Text2TxtTest {

    @Test(timeout = 60000)
    public void testSubjectWord1For() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        String word1 = "for";
        String word2 = "abc";
        String word3 = "def";

        given()
            .pathParam("word1", word1)
            .pathParam("word2", word2)
            .pathParam("word3", word3)
        .when()
            .get(baseUrl + "/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectSeeYou() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        String word1 = "see";
        String word2 = "you";
        String word3 = "def";

        given()
            .pathParam("word1", word1)
            .pathParam("word2", word2)
            .pathParam("word3", word3)
        .when()
            .get(baseUrl + "/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectByTheWay() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        String word1 = "by";
        String word2 = "the";
        String word3 = "way";

        given()
            .pathParam("word1", word1)
            .pathParam("word2", word2)
            .pathParam("word3", word3)
        .when()
            .get(baseUrl + "/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200);
    }
}