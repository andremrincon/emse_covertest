package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TitleTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testMaleWithValidTitle() {
        given()
            .when()
            .get("/api/title/male/mr")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleWithInvalidTitle() {
        given()
            .when()
            .get("/api/title/male/invalidtitle")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleWithValidTitle() {
        given()
            .when()
            .get("/api/title/female/mrs")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleWithInvalidTitle() {
        given()
            .when()
            .get("/api/title/female/invalidtitle")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneWithInvalidTitle() {
        given()
            .when()
            .get("/api/title/none/invalidtitle")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOtherSex() {
        given()
            .when()
            .get("/api/title/other/anytitle")
            .then()
            .statusCode(200);
    }
}