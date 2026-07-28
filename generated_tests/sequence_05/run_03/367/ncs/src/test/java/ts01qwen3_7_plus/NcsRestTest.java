package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class NcsRestTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testFisherWithMGreaterThan1000() {
        given()
            .when()
            .get("/api/fisher/1001/5/0.75")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherWithNGreaterThan1000() {
        given()
            .when()
            .get("/api/fisher/10/1001/0.75")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherWithValidParameters() {
        given()
            .when()
            .get("/api/fisher/10/5/0.75")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderWithAGreaterThanLimit() {
        given()
            .when()
            .get("/api/remainder/10001/5")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderWithBGreaterThanLimit() {
        given()
            .when()
            .get("/api/remainder/17/10001")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderWithValidParameters() {
        given()
            .when()
            .get("/api/remainder/17/5")
            .then()
            .statusCode(200);
    }
}