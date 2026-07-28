package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testBessjValidInput() {
        given()
            .pathParam("n", 3)
            .pathParam("x", 2.5)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjInvalidNTooSmall() {
        given()
            .pathParam("n", 2)
            .pathParam("x", 2.5)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjInvalidNTooLarge() {
        given()
            .pathParam("n", 1001)
            .pathParam("x", 2.5)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherValidInput() {
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
    public void testFisherInvalidMTooLarge() {
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
    public void testFisherInvalidNTooLarge() {
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
    public void testGammqValidInput() {
        given()
            .pathParam("a", 5.5)
            .pathParam("x", 2.3)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidInput() {
        given()
            .pathParam("a", -1.0)
            .pathParam("x", 2.3)
        .when()
            .get("/api/gammq/{a}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderValidInput() {
        given()
            .pathParam("a", 17)
            .pathParam("b", 5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderInvalidATooLarge() {
        given()
            .pathParam("a", 10001)
            .pathParam("b", 5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderInvalidATooSmall() {
        given()
            .pathParam("a", -10001)
            .pathParam("b", 5)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderInvalidBTooLarge() {
        given()
            .pathParam("a", 17)
            .pathParam("b", 10001)
        .when()
            .get("/api/remainder/{a}/{b}")
        .then()
            .statusCode(400);
    }
}