package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @Test(timeout = 60000)
    public void testSubjectSeeYou() {
        given()
            .pathParam("word1", "see")
            .pathParam("word2", "you")
            .pathParam("word3", "later")
        .when()
            .get("/api/text2txt/{word1}/{word2}/{word3}")
        .then()
            .statusCode(200)
            .body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testSubjectByTheWay() {
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