package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Text2TxtTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testTwo() {
        given()
            .when()
                .get("/api/text2txt/two/word2/word3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFor() {
        given()
            .when()
                .get("/api/text2txt/for/word2/word3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testYou() {
        given()
            .when()
                .get("/api/text2txt/you/word2/word3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAnd() {
        given()
            .when()
                .get("/api/text2txt/and/word2/word3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAre() {
        given()
            .when()
                .get("/api/text2txt/are/word2/word3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testByTheWay() {
        given()
            .when()
                .get("/api/text2txt/by/the/way")
            .then()
                .statusCode(200);
    }
}