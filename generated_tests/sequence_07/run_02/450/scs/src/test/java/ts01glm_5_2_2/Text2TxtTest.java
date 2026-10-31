package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testWord1TwoReturnsAbbreviation2() {
        given()
        .when()
            .get("/api/text2txt/two/quick/brown")
        .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWord1ForReturnsAbbreviation4() {
        given()
        .when()
            .get("/api/text2txt/for/quick/brown")
        .then()
            .statusCode(200)
            .body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testWord1YouReturnsAbbreviationU() {
        given()
        .when()
            .get("/api/text2txt/you/quick/brown")
        .then()
            .statusCode(200)
            .body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testWord1AreReturnsAbbreviationR() {
        given()
        .when()
            .get("/api/text2txt/are/quick/brown")
        .then()
            .statusCode(200)
            .body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsAbbreviationCu() {
        given()
        .when()
            .get("/api/text2txt/see/you/soon")
        .then()
            .statusCode(200)
            .body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsAbbreviationBtw() {
        given()
        .when()
            .get("/api/text2txt/by/the/way")
        .then()
            .statusCode(200)
            .body(equalTo("btw"));
    }
}