package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ExpintTest {

    @Test(timeout = 60000)
    public void testExpintNegativeN() {
        given()
            .when()
                .get("http://localhost:8080/api/expint/-1/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintZeroN() {
        given()
            .when()
                .get("http://localhost:8080/api/expint/0/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintZeroX() {
        given()
            .when()
                .get("http://localhost:8080/api/expint/2/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintContinuedFraction() {
        given()
            .when()
                .get("http://localhost:8080/api/expint/3/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeries() {
        given()
            .when()
                .get("http://localhost:8080/api/expint/3/0.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintNegativeX() {
        given()
            .when()
                .get("http://localhost:8080/api/expint/3/-1.0")
            .then()
                .statusCode(400);
    }
}