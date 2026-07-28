package ts01qwen3_7_plus;

import static io.restassured.RestAssured.given;

import org.junit.Test;

public class BessjTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testBessjReturns400WhenNLessThan2() {
        given()
            .pathParam("n", 1)
            .pathParam("x", 2.5)
        .when()
            .get(BASE_URL + "/api/bessj/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjReturns200WhenXIsNegativeAndAbsXGreaterThanN() {
        given()
            .pathParam("n", 3)
            .pathParam("x", -10.0)
        .when()
            .get(BASE_URL + "/api/bessj/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjReturns200WhenXIsPositiveAndAbsXGreaterThanN() {
        given()
            .pathParam("n", 3)
            .pathParam("x", 10.0)
        .when()
            .get(BASE_URL + "/api/bessj/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjReturns200WhenAbsXLessThanOrEqualToN() {
        given()
            .pathParam("n", 3)
            .pathParam("x", 2.5)
        .when()
            .get(BASE_URL + "/api/bessj/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjReturns200WhenXIsZero() {
        given()
            .pathParam("n", 3)
            .pathParam("x", 0.0)
        .when()
            .get(BASE_URL + "/api/bessj/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjReturns400WhenNIsInvalid() {
        given()
            .pathParam("n", "abc")
            .pathParam("x", 2.5)
        .when()
            .get(BASE_URL + "/api/bessj/{n}/{x}")
        .then()
            .statusCode(400);
    }
}