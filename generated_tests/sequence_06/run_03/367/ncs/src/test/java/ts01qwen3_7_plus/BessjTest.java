package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class BessjTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testBessjThrowsForNLessThan2() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/bessj/1/2.5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjReturnsZeroForXZero() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/bessj/3/0.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjForwardRecurrenceWithSmallX() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/bessj/3/5.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjForwardRecurrenceWithLargeNegativeX() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/bessj/3/-10.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjBackwardRecurrenceWithNegativeX() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/bessj/3/-2.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjBackwardRecurrenceWithPositiveX() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/bessj/3/2.0")
        .then()
            .statusCode(200);
    }
}