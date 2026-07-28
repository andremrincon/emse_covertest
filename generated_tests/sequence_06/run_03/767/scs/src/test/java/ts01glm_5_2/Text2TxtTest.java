package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;

public class Text2TxtTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testSubjectTwo() {
        given()
            .when()
                .get("/api/text2txt/two/word/word")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testSubjectFor() {
        given()
            .when()
                .get("/api/text2txt/for/word/word")
            .then()
                .statusCode(200)
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testSubjectYou() {
        given()
            .when()
                .get("/api/text2txt/you/word/word")
            .then()
                .statusCode(200)
                .body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testSubjectAre() {
        given()
            .when()
                .get("/api/text2txt/are/word/word")
            .then()
                .statusCode(200)
                .body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testSubjectSeeYou() {
        given()
            .when()
                .get("/api/text2txt/see/you/word")
            .then()
                .statusCode(200)
                .body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testSubjectByTheWay() {
        given()
            .when()
                .get("/api/text2txt/by/the/way")
            .then()
                .statusCode(200)
                .body(equalTo("btw"));
    }
}