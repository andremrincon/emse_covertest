package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TitleTest {

    private static String baseUrl;

    @BeforeClass
    public static void setup() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testMaleValidTitle() {
        given()
            .when()
                .get("/api/title/male/mr")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleInvalidTitle() {
        given()
            .when()
                .get("/api/title/male/mrs")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleValidTitle() {
        given()
            .when()
                .get("/api/title/female/mrs")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleInvalidTitle() {
        given()
            .when()
                .get("/api/title/female/mr")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneValidTitle() {
        given()
            .when()
                .get("/api/title/none/dr")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneInvalidTitle() {
        given()
            .when()
                .get("/api/title/none/mr")
            .then()
                .statusCode(200);
    }
}