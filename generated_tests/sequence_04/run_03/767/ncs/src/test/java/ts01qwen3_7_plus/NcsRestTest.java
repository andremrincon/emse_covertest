package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class NcsRestTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("BASE_URL", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testFisherReturns400WhenMExceeds1000() {
        given()
            .pathParam("m", 1001)
            .pathParam("n", 5)
            .pathParam("x", 0.75)
        .when()
            .get("/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherReturns400WhenNExceeds1000() {
        given()
            .pathParam("m", 10)
            .pathParam("n", 1001)
            .pathParam("x", 0.75)
        .when()
            .get("/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherReturns200WithValidParameters() {
        given()
            .pathParam("m", 10)
            .pathParam("n", 5)
            .pathParam("x", 0.75)
        .when()
            .get("/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderReturns400WhenAExceedsLimit() {
        given()
            .pathParam("a", 10001)
            .pathParam("b", 5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderReturns400WhenALessThanNegativeLimit() {
        given()
            .pathParam("a", -10001)
            .pathParam("b", 5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderReturns200WithValidParameters() {
        given()
            .pathParam("a", 17)
            .pathParam("b", 5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200);
    }
}