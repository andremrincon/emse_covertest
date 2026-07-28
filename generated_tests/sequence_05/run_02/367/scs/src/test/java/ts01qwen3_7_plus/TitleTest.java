package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TitleTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testMaleMatch() {
        given()
            .when()
            .get("/api/title/{sex}/{title}", "male", "mr")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleNoMatch() {
        given()
            .when()
            .get("/api/title/{sex}/{title}", "male", "xyz")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleMatch() {
        given()
            .when()
            .get("/api/title/{sex}/{title}", "female", "mrs")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleNoMatch() {
        given()
            .when()
            .get("/api/title/{sex}/{title}", "female", "xyz")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneMatch() {
        given()
            .when()
            .get("/api/title/{sex}/{title}", "none", "dr")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneNoMatch() {
        given()
            .when()
            .get("/api/title/{sex}/{title}", "none", "xyz")
            .then()
            .statusCode(200);
    }
}