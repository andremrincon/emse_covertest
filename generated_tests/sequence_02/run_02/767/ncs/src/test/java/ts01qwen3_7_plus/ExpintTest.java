package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ExpintTest {

    static {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testExpintNegativeNAndX() {
        given()
            .pathParam("n", -1)
            .pathParam("x", -2.5)
        .when()
            .get("/api/expint/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintZeroXAndZeroN() {
        given()
            .pathParam("n", 0)
            .pathParam("x", 0.0)
        .when()
            .get("/api/expint/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintZeroN() {
        given()
            .pathParam("n", 0)
            .pathParam("x", 2.5)
        .when()
            .get("/api/expint/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintZeroX() {
        given()
            .pathParam("n", 2)
            .pathParam("x", 0.0)
        .when()
            .get("/api/expint/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXGreaterThanOne() {
        given()
            .pathParam("n", 1)
            .pathParam("x", 2.5)
        .when()
            .get("/api/expint/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXLessThanOrEqualToOne() {
        given()
            .pathParam("n", 1)
            .pathParam("x", 0.5)
        .when()
            .get("/api/expint/{n}/{x}")
        .then()
            .statusCode(200);
    }
}