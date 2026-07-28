package ts01qwen3_7_plus;

import org.junit.Before;
import org.junit.Test;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class TitleTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
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
    public void testFemaleWithValidTitle() {
        given()
            .when()
            .get("/api/title/female/mrs")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneWithValidTitle() {
        given()
            .when()
            .get("/api/title/none/dr")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleWithInvalidTitle() {
        given()
            .when()
            .get("/api/title/male/invalid")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleWithInvalidTitle() {
        given()
            .when()
            .get("/api/title/female/invalid")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneWithInvalidTitle() {
        given()
            .when()
            .get("/api/title/none/invalid")
            .then()
            .statusCode(200);
    }
}