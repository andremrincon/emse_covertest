package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testSubjectFor() {
        given()
            .when()
                .get("/api/text2txt/for/word2/word3")
            .then()
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testSubjectFour() {
        given()
            .when()
                .get("/api/text2txt/four/word2/word3")
            .then()
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testSubjectAnd() {
        given()
            .when()
                .get("/api/text2txt/and/word2/word3")
            .then()
                .body(equalTo("n"));
    }

    @Test(timeout = 60000)
    public void testSubjectAre() {
        given()
            .when()
                .get("/api/text2txt/are/word2/word3")
            .then()
                .body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testSubjectSeeYou() {
        given()
            .when()
                .get("/api/text2txt/see/you/word3")
            .then()
                .body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testSubjectByTheWay() {
        given()
            .when()
                .get("/api/text2txt/by/the/way")
            .then()
                .body(equalTo("btw"));
    }
}