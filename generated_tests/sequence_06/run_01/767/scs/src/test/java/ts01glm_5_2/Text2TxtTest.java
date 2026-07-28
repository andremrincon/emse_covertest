package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {
    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testSubjectTwo() {
        given()
            .when()
                .get("/api/text2txt/two/word/word")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectFor() {
        given()
            .when()
                .get("/api/text2txt/for/word/word")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectYou() {
        given()
            .when()
                .get("/api/text2txt/you/word/word")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectAnd() {
        given()
            .when()
                .get("/api/text2txt/and/word/word")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectAre() {
        given()
            .when()
                .get("/api/text2txt/are/word/word")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectSeeYou() {
        given()
            .when()
                .get("/api/text2txt/see/you/word")
            .then()
                .statusCode(200);
    }
}