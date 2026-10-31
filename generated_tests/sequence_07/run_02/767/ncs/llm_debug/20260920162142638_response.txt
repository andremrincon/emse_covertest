package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testFisherSuccess() {
        given()
            .accept("application/json")
        .when()
            .get("/api/fisher/10/5/0.75")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherMExceedsLimit() {
        given()
            .accept("application/json")
        .when()
            .get("/api/fisher/1001/5/0.75")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherExceptionPath() {
        given()
            .accept("application/json")
        .when()
            .get("/api/fisher/10/-3/0.75")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderSuccess() {
        given()
            .accept("application/json")
        .when()
            .get("/api/remainder/17/5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderExceedsPositiveLimit() {
        given()
            .accept("application/json")
        .when()
            .get("/api/remainder/10001/5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderExceedsNegativeLimit() {
        given()
            .accept("application/json")
        .when()
            .get("/api/remainder/17/-10001")
        .then()
            .statusCode(400);
    }
}