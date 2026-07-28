package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testBessjValidInput() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/bessj/3/2.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjInvalidNLessThan2() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/bessj/1/2.5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjInvalidNGreaterThan1000() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/bessj/1001/2.5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherValidInput() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/fisher/10/5/0.75")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherCatchBlock() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/fisher/1/1/-1.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderValidInput() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/remainder/17/5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderExceedsPositiveLimit() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/remainder/10001/5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderExceedsNegativeLimit() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/remainder/-10001/5")
        .then()
            .statusCode(400);
    }
}