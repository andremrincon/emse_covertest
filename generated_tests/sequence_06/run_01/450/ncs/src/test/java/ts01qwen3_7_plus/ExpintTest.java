package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ExpintTest {

    private static final String BASE_URL = "http://localhost:8080";

    @Test(timeout = 60000)
    public void testExpintValidXGreaterThan1() {
        given()
            .when()
            .get(BASE_URL + "/api/expint/3/2.5")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintValidXLessThanOrEqual1() {
        given()
            .when()
            .get(BASE_URL + "/api/expint/3/0.1")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintInvalidNLessThan0() {
        given()
            .when()
            .get(BASE_URL + "/api/expint/-1/2.5")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintInvalidXLessThan0() {
        given()
            .when()
            .get(BASE_URL + "/api/expint/3/-999.9")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintInvalidXZeroNZero() {
        given()
            .when()
            .get(BASE_URL + "/api/expint/0/0.0")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintInvalidXZeroNOne() {
        given()
            .when()
            .get(BASE_URL + "/api/expint/1/0.0")
            .then()
            .statusCode(400);
    }
}