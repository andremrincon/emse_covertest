package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @Test(timeout = 60000)
    public void testWord1Two() {
        given()
            .when()
                .get("/api/text2txt/two/a/b")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWord1For() {
        given()
            .when()
                .get("/api/text2txt/for/a/b")
            .then()
                .statusCode(200)
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testWord1You() {
        given()
            .when()
                .get("/api/text2txt/you/a/b")
            .then()
                .statusCode(200)
                .body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testWord1And() {
        given()
            .when()
                .get("/api/text2txt/and/a/b")
            .then()
                .statusCode(200)
                .body(equalTo("n"));
    }

    @Test(timeout = 60000)
    public void testWord1Are() {
        given()
            .when()
                .get("/api/text2txt/are/a/b")
            .then()
                .statusCode(200)
                .body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testWord1SeeWord2You() {
        given()
            .when()
                .get("/api/text2txt/see/you/b")
            .then()
                .statusCode(200)
                .body(equalTo("cu"));
    }
}