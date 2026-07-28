package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class NcsRestTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testFisherReturns400WhenMGreaterThan1000() {
        given()
        .when()
            .get(baseUrl + "/api/fisher/1001/5/0.75")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherReturns400WhenNGreaterThan1000() {
        given()
        .when()
            .get(baseUrl + "/api/fisher/10/1001/0.75")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderReturns400WhenAGreaterThan10000() {
        given()
        .when()
            .get(baseUrl + "/api/remainder/10001/5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderReturns400WhenALessThanMinus10000() {
        given()
        .when()
            .get(baseUrl + "/api/remainder/-10001/5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderReturns400WhenBGreaterThan10000() {
        given()
        .when()
            .get(baseUrl + "/api/remainder/17/10001")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderReturns400WhenBLessThanMinus10000() {
        given()
        .when()
            .get(baseUrl + "/api/remainder/17/-10001")
        .then()
            .statusCode(400);
    }
}