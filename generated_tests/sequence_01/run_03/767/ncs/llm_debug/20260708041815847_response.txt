package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ExpintTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testExpint_InvalidN() {
        given()
            .pathParam("n", -1)
            .pathParam("x", 1.0)
        .when()
            .get("/api/expint/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_NZero_XPositive() {
        given()
            .pathParam("n", 0)
            .pathParam("x", 1.0)
        .when()
            .get("/api/expint/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_NPositive_XZero() {
        given()
            .pathParam("n", 2)
            .pathParam("x", 0.0)
        .when()
            .get("/api/expint/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_NPositive_XGreaterThanOne() {
        given()
            .pathParam("n", 1)
            .pathParam("x", 2.0)
        .when()
            .get("/api/expint/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_NOne_XLessThanOne() {
        given()
            .pathParam("n", 1)
            .pathParam("x", 0.5)
        .when()
            .get("/api/expint/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_NTwo_XLessThanOne() {
        given()
            .pathParam("n", 2)
            .pathParam("x", 0.5)
        .when()
            .get("/api/expint/{n}/{x}")
        .then()
            .statusCode(200);
    }
}