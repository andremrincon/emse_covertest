package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testWord1Two() {
        given()
            .when()
                .get(baseUrl + "/api/text2txt/two/x/y")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWord1For() {
        given()
            .when()
                .get(baseUrl + "/api/text2txt/for/x/y")
            .then()
                .statusCode(200)
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testWord1Are() {
        given()
            .when()
                .get(baseUrl + "/api/text2txt/are/x/y")
            .then()
                .statusCode(200)
                .body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testWord1SeeWord2You() {
        given()
            .when()
                .get(baseUrl + "/api/text2txt/see/you/y")
            .then()
                .statusCode(200)
                .body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testWord1ByWord2TheWord3Way() {
        given()
            .when()
                .get(baseUrl + "/api/text2txt/by/the/way")
            .then()
                .statusCode(200)
                .body(equalTo("btw"));
    }

    @Test(timeout = 60000)
    public void testWord1Four() {
        given()
            .when()
                .get(baseUrl + "/api/text2txt/four/x/y")
            .then()
                .statusCode(200)
                .body(equalTo("4"));
    }
}