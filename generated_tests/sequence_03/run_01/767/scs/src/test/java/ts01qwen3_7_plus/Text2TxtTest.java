package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testWord1For() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/text2txt/for/you/test")
        .then()
            .statusCode(200)
            .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testWord1Four() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/text2txt/four/you/test")
        .then()
            .statusCode(200)
            .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testSeeYou() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/text2txt/see/you/test")
        .then()
            .statusCode(200)
            .body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testSeeNotYou() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/text2txt/see/me/test")
        .then()
            .statusCode(200)
            .body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testByTheWay() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/text2txt/by/the/way")
        .then()
            .statusCode(200)
            .body(equalTo("btw"));
    }

    @Test(timeout = 60000)
    public void testByNotThe() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/text2txt/by/me/way")
        .then()
            .statusCode(200)
            .body(equalTo(""));
    }
}