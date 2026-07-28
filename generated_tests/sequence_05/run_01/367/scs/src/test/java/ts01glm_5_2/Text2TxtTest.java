package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
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
                .get("/api/text2txt/two/x/y")
            .then()
                .body(containsString("2"));
    }

    @Test(timeout = 60000)
    public void testSubjectFour() {
        given()
            .when()
                .get("/api/text2txt/four/x/y")
            .then()
                .body(containsString("4"));
    }

    @Test(timeout = 60000)
    public void testSubjectAnd() {
        given()
            .when()
                .get("/api/text2txt/and/x/y")
            .then()
                .body(containsString("n"));
    }

    @Test(timeout = 60000)
    public void testSubjectAre() {
        given()
            .when()
                .get("/api/text2txt/are/x/y")
            .then()
                .body(containsString("r"));
    }

    @Test(timeout = 60000)
    public void testSubjectSeeYou() {
        given()
            .when()
                .get("/api/text2txt/see/you/y")
            .then()
                .body(containsString("cu"));
    }

    @Test(timeout = 60000)
    public void testSubjectByTheWay() {
        given()
            .when()
                .get("/api/text2txt/by/the/way")
            .then()
                .body(containsString("btw"));
    }
}