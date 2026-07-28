package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ExpintTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testExpintNegativeNAndX() {
        given()
            .pathParam("n", -1)
            .pathParam("x", -1.0)
        .when()
            .get("/api/expint/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintZeroNAndZeroX() {
        given()
            .pathParam("n", 0)
            .pathParam("x", 0.0)
        .when()
            .get("/api/expint/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintZeroNPositiveX() {
        given()
            .pathParam("n", 0)
            .pathParam("x", 2.5)
        .when()
            .get("/api/expint/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintPositiveNXZero() {
        given()
            .pathParam("n", 2)
            .pathParam("x", 0.0)
        .when()
            .get("/api/expint/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintPositiveNXGreaterThanOne() {
        given()
            .pathParam("n", 1)
            .pathParam("x", 2.5)
        .when()
            .get("/api/expint/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintPositiveNXLessThanOne() {
        given()
            .pathParam("n", 1)
            .pathParam("x", 0.5)
        .when()
            .get("/api/expint/{n}/{x}")
        .then()
            .statusCode(200);
    }
}