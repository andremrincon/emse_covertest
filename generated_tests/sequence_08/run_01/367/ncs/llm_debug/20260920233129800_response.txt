package ts01qwen3_7_plus_2;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class ExpintTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testExpintNegativeN() {
        given()
            .pathParam("n", -1)
            .pathParam("x", 2.5)
        .when()
            .get(baseUrl + "/api/expint/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintZeroNZeroX() {
        given()
            .pathParam("n", 0)
            .pathParam("x", 0.0)
        .when()
            .get(baseUrl + "/api/expint/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintZeroN() {
        given()
            .pathParam("n", 0)
            .pathParam("x", 2.5)
        .when()
            .get(baseUrl + "/api/expint/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintZeroX() {
        given()
            .pathParam("n", 2)
            .pathParam("x", 0.0)
        .when()
            .get(baseUrl + "/api/expint/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXGreaterThanOne() {
        given()
            .pathParam("n", 3)
            .pathParam("x", 2.5)
        .when()
            .get(baseUrl + "/api/expint/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXLessThanOrEqualToOne() {
        given()
            .pathParam("n", 3)
            .pathParam("x", 0.5)
        .when()
            .get(baseUrl + "/api/expint/{n}/{x}")
        .then()
            .statusCode(200);
    }
}