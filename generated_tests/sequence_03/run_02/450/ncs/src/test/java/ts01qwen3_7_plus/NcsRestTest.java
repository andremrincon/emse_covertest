package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class NcsRestTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testBessjSuccess() {
        given()
            .pathParam("n", 3)
            .pathParam("x", 2.5)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjErrorNTooSmall() {
        given()
            .pathParam("n", -5)
            .pathParam("x", 2.5)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjErrorNTooLarge() {
        given()
            .pathParam("n", 1001)
            .pathParam("x", 2.5)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherSuccess() {
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
    public void testFisherErrorMTooLarge() {
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
    public void testFisherErrorNTooLarge() {
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
    public void testRemainderSuccess() {
        given()
            .pathParam("a", 17)
            .pathParam("b", 5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderErrorALimitExceeded() {
        given()
            .pathParam("a", 10001)
            .pathParam("b", 5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderErrorBLimitExceeded() {
        given()
            .pathParam("a", 17)
            .pathParam("b", 10001)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(400);
    }
}