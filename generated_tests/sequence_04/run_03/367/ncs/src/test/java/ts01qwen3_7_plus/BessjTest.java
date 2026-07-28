package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class BessjTest {

    private static final String BASE_URL = "http://localhost:8080";

    @Test(timeout = 60000)
    public void testBessjWithNLessThan2() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/bessj/1/2.5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjWithXEqualToZero() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/bessj/3/0.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithAbsXGreaterThanNAndAbsXLessThan8() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/bessj/3/5.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithAbsXGreaterThanNAndAbsXGreaterThanOrEqualTo8AndPositiveX() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/bessj/3/10.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithAbsXGreaterThanNAndAbsXGreaterThanOrEqualTo8AndNegativeX() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/bessj/3/-10.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithAbsXLessThanOrEqualToN() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/bessj/10/5.0")
        .then()
            .statusCode(200);
    }
}