package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class Text2TxtTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testTwoBranch() {
        given()
            .when()
                .get("/api/text2txt/two/test/word")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testForBranch() {
        given()
            .when()
                .get("/api/text2txt/for/test/word")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testYouBranch() {
        given()
            .when()
                .get("/api/text2txt/you/test/word")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAndBranch() {
        given()
            .when()
                .get("/api/text2txt/and/test/word")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAreBranch() {
        given()
            .when()
                .get("/api/text2txt/are/test/word")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSeeYouBranch() {
        given()
            .when()
                .get("/api/text2txt/see/you/test")
            .then()
                .statusCode(200);
    }
}