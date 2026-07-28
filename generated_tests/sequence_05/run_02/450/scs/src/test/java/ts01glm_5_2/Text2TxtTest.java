package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Text2TxtTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testText2TxtWordTwo() {
        given()
            .when()
                .get("/api/text2txt/two/word2/word3")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testText2TxtWordFor() {
        given()
            .when()
                .get("/api/text2txt/for/word2/word3")
            .then()
                .statusCode(200)
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testText2TxtWordYou() {
        given()
            .when()
                .get("/api/text2txt/you/word2/word3")
            .then()
                .statusCode(200)
                .body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testText2TxtWordAre() {
        given()
            .when()
                .get("/api/text2txt/are/word2/word3")
            .then()
                .statusCode(200)
                .body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testText2TxtSeeYou() {
        given()
            .when()
                .get("/api/text2txt/see/you/word3")
            .then()
                .statusCode(200)
                .body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testText2TxtByTheWay() {
        given()
            .when()
                .get("/api/text2txt/by/the/way")
            .then()
                .statusCode(200)
                .body(equalTo("btw"));
    }
}