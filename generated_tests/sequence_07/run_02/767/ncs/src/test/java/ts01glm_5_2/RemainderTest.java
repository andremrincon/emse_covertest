package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.*;

public class RemainderTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testRemainderBothPositiveLoopEnters() {
        given()
        .when()
            .get("/api/remainder/17/5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveNegativeLoopEnters() {
        given()
        .when()
            .get("/api/remainder/17/-5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativePositiveLoopEnters() {
        given()
        .when()
            .get("/api/remainder/-15/5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderBothNegativeLoopEnters() {
        given()
        .when()
            .get("/api/remainder/-9/-3")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderBothPositiveLoopNotEnter() {
        given()
        .when()
            .get("/api/remainder/2/5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveNegativeLoopNotEnter() {
        given()
        .when()
            .get("/api/remainder/2/-5")
        .then()
            .statusCode(200);
    }
}