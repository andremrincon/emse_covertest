package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class NcsRestTest {

    private final String baseUrl = System.getProperty("test.base.url", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testBessjValid() {
        given()
            .baseUri(baseUrl)
            .pathParam("n", 3)
            .pathParam("x", 2.5)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjInvalidN() {
        given()
            .baseUri(baseUrl)
            .pathParam("n", 2)
            .pathParam("x", 2.5)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherValid() {
        given()
            .baseUri(baseUrl)
            .pathParam("m", 10)
            .pathParam("n", 5)
            .pathParam("x", 0.75)
        .when()
            .get("/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidM() {
        given()
            .baseUri(baseUrl)
            .pathParam("m", 1001)
            .pathParam("n", 5)
            .pathParam("x", 0.75)
        .when()
            .get("/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderValid() {
        given()
            .baseUri(baseUrl)
            .pathParam("a", 17)
            .pathParam("b", 5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderInvalidA() {
        given()
            .baseUri(baseUrl)
            .pathParam("a", 10001)
            .pathParam("b", 5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(400);
    }
}