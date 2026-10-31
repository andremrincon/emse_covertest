package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Text2TxtTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testTwo() {
        given()
            .when()
                .get("/api/text2txt/two/test/test")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFour() {
        given()
            .when()
                .get("/api/text2txt/four/test/test")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testYou() {
        given()
            .when()
                .get("/api/text2txt/you/test/test")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAnd() {
        given()
            .when()
                .get("/api/text2txt/and/test/test")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAre() {
        given()
            .when()
                .get("/api/text2txt/are/test/test")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSeeYou() {
        given()
            .when()
                .get("/api/text2txt/see/you/test")
            .then()
                .statusCode(200);
    }
}