package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class BessjTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testBessjNLessThan2() {
        given()
            .when()
                .get(baseUrl + "/api/bessj/1/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjXZero() {
        given()
            .when()
                .get(baseUrl + "/api/bessj/3/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanNAndXNegative() {
        given()
            .when()
                .get(baseUrl + "/api/bessj/3/-10.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxLessThanOrEqualToN() {
        given()
            .when()
                .get(baseUrl + "/api/bessj/10/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanNAndXPositive() {
        given()
            .when()
                .get(baseUrl + "/api/bessj/3/10.0")
            .then()
                .statusCode(200);
    }
}