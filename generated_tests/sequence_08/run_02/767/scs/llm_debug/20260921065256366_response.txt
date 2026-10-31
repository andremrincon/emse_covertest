package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class Text2TxtTest {

    private String getBaseUrl() {
        String envUrl = System.getenv("BASE_URL");
        return (envUrl != null && !envUrl.isEmpty()) ? envUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testTwo() {
        given()
            .when()
            .get(getBaseUrl() + "/api/text2txt/two/a/b")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFor() {
        given()
            .when()
            .get(getBaseUrl() + "/api/text2txt/for/a/b")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testYou() {
        given()
            .when()
            .get(getBaseUrl() + "/api/text2txt/you/a/b")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAnd() {
        given()
            .when()
            .get(getBaseUrl() + "/api/text2txt/and/a/b")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAre() {
        given()
            .when()
            .get(getBaseUrl() + "/api/text2txt/are/a/b")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSeeYou() {
        given()
            .when()
            .get(getBaseUrl() + "/api/text2txt/see/you/b")
            .then()
            .statusCode(200);
    }
}