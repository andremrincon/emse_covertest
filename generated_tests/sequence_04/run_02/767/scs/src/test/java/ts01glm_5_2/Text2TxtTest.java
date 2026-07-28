package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSubjectForReturnsFour() {
        given()
            .when()
                .get("/api/text2txt/for/quick/brown")
            .then()
                .statusCode(200)
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testSubjectFourReturnsFour() {
        given()
            .when()
                .get("/api/text2txt/four/quick/brown")
            .then()
                .statusCode(200)
                .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testSubjectYouReturnsU() {
        given()
            .when()
                .get("/api/text2txt/you/quick/brown")
            .then()
                .statusCode(200)
                .body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testSubjectAndReturnsN() {
        given()
            .when()
                .get("/api/text2txt/and/quick/brown")
            .then()
                .statusCode(200)
                .body(equalTo("n"));
    }

    @Test(timeout = 60000)
    public void testSubjectSeeYouReturnsCu() {
        given()
            .when()
                .get("/api/text2txt/see/you/brown")
            .then()
                .statusCode(200)
                .body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testSubjectByTheWayReturnsBtw() {
        given()
            .when()
                .get("/api/text2txt/by/the/way")
            .then()
                .statusCode(200)
                .body(equalTo("btw"));
    }
}