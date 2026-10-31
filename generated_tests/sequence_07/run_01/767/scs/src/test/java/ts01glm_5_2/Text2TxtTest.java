package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Text2TxtTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testTwoAbbreviation() {
        given()
        .when()
            .get("/api/text2txt/two/foo/bar")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testForAbbreviation() {
        given()
        .when()
            .get("/api/text2txt/for/foo/bar")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testYouAbbreviation() {
        given()
        .when()
            .get("/api/text2txt/you/foo/bar")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAndAbbreviation() {
        given()
        .when()
            .get("/api/text2txt/and/foo/bar")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAreAbbreviation() {
        given()
        .when()
            .get("/api/text2txt/are/foo/bar")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSeeYouAbbreviation() {
        given()
        .when()
            .get("/api/text2txt/see/you/bar")
        .then()
            .statusCode(200);
    }
}