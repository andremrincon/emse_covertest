package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TitleTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testMaleWithValidMaleTitle() {
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
            .get("/api/title/male/mrs")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleWithValidFemaleTitle() {
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
            .get("/api/title/female/mr")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneWithValidNeutralTitle() {
        given()
            .when()
            .get("/api/title/none/dr")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneWithInvalidTitle() {
        given()
            .when()
            .get("/api/title/none/mr")
            .then()
            .statusCode(200);
    }
}