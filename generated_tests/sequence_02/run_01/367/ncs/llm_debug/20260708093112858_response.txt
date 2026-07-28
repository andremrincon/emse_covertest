package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class ExpintTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testExpintSuccessXGreaterThan1() {
        given()
            .pathParam("n", 3)
            .pathParam("x", 2.5)
        .when()
            .get(BASE_URL + "/api/expint/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSuccessXLessThan1() {
        given()
            .pathParam("n", 3)
            .pathParam("x", 0.1)
        .when()
            .get(BASE_URL + "/api/expint/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintInvalidN() {
        given()
            .pathParam("n", -1)
            .pathParam("x", 2.5)
        .when()
            .get(BASE_URL + "/api/expint/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintInvalidX() {
        given()
            .pathParam("n", 3)
            .pathParam("x", -999.9)
        .when()
            .get(BASE_URL + "/api/expint/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintZeroX() {
        given()
            .pathParam("n", 2)
            .pathParam("x", 0.0)
        .when()
            .get(BASE_URL + "/api/expint/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintZeroN() {
        given()
            .pathParam("n", 0)
            .pathParam("x", 2.5)
        .when()
            .get(BASE_URL + "/api/expint/{n}/{x}")
        .then()
            .statusCode(200);
    }
}