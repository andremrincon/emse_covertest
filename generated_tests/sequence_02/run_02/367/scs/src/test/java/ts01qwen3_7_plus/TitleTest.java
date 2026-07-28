package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TitleTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null) {
            baseUrl = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testMaleWithMrTitle() {
        given()
            .when()
            .get("/api/title/male/mr")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleWithProfTitle() {
        given()
            .when()
            .get("/api/title/male/prof")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleWithMrsTitle() {
        given()
            .when()
            .get("/api/title/female/mrs")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleWithProfTitle() {
        given()
            .when()
            .get("/api/title/female/prof")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneWithDrTitle() {
        given()
            .when()
            .get("/api/title/none/dr")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneWithProfTitle() {
        given()
            .when()
            .get("/api/title/none/prof")
            .then()
            .statusCode(200);
    }
}