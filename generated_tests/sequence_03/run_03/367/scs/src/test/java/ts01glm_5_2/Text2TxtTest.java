package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Text2TxtTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testSubject_two() {
        given()
            .when()
            .get("/api/text2txt/two/x/y")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_for() {
        given()
            .when()
            .get("/api/text2txt/for/x/y")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_you() {
        given()
            .when()
            .get("/api/text2txt/you/x/y")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_are() {
        given()
            .when()
            .get("/api/text2txt/are/x/y")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_seeYou() {
        given()
            .when()
            .get("/api/text2txt/see/you/x")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubject_byTheWay() {
        given()
            .when()
            .get("/api/text2txt/by/the/way")
            .then()
            .statusCode(200);
    }
}