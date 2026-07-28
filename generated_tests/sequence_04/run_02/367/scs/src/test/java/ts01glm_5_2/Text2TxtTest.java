package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Text2TxtTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testTwoReturns2() {
        given()
            .when()
            .get("/api/text2txt/two/word2/word3")
            .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testForReturns4() {
        given()
            .when()
            .get("/api/text2txt/for/word2/word3")
            .then()
            .statusCode(200)
            .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testYouReturnsU() {
        given()
            .when()
            .get("/api/text2txt/you/word2/word3")
            .then()
            .statusCode(200)
            .body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testAreReturnsR() {
        given()
            .when()
            .get("/api/text2txt/are/word2/word3")
            .then()
            .statusCode(200)
            .body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        given()
            .when()
            .get("/api/text2txt/see/you/word3")
            .then()
            .statusCode(200)
            .body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBtw() {
        given()
            .when()
            .get("/api/text2txt/by/the/way")
            .then()
            .statusCode(200)
            .body(equalTo("btw"));
    }
}