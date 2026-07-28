package ts01qwen3_7_plus;

import static io.restassured.RestAssured.given;

import org.junit.Test;

public class ExpintTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testExpintNegativeN() {
        given()
            .when()
            .get(BASE_URL + "/api/expint/-1/2.5")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintNegativeX() {
        given()
            .when()
            .get(BASE_URL + "/api/expint/3/-1.0")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintZeroXZeroN() {
        given()
            .when()
            .get(BASE_URL + "/api/expint/0/0.0")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintZeroXOneN() {
        given()
            .when()
            .get(BASE_URL + "/api/expint/1/0.0")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintXGreaterThanOne() {
        given()
            .when()
            .get(BASE_URL + "/api/expint/3/2.5")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXLessThanOrEqualToOne() {
        given()
            .when()
            .get(BASE_URL + "/api/expint/3/0.1")
            .then()
            .statusCode(200);
    }
}