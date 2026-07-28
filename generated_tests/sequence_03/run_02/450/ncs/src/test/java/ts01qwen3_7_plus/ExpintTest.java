package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ExpintTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testExpintXGreaterThan1() {
        given()
            .pathParam("n", 3)
            .pathParam("x", 2.5)
        .when()
            .get("/api/expint/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXLessThan1NGreaterThan1() {
        given()
            .pathParam("n", 3)
            .pathParam("x", 0.5)
        .when()
            .get("/api/expint/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXLessThan1NEquals1() {
        given()
            .pathParam("n", 1)
            .pathParam("x", 0.5)
        .when()
            .get("/api/expint/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintNEquals0() {
        given()
            .pathParam("n", 0)
            .pathParam("x", 2.5)
        .when()
            .get("/api/expint/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXEquals0() {
        given()
            .pathParam("n", 3)
            .pathParam("x", 0.0)
        .when()
            .get("/api/expint/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintInvalidN() {
        given()
            .pathParam("n", -1)
            .pathParam("x", 2.5)
        .when()
            .get("/api/expint/{n}/{x}")
        .then()
            .statusCode(400);
    }
}