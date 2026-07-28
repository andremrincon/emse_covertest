package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class NcsRestTest {

    @BeforeClass
    public static void setUp() {
        String host = System.getProperty("test.host", "localhost");
        String port = System.getProperty("test.port", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
    }

    @Test(timeout = 60000)
    public void testBessjNormalCase() {
        given()
            .when()
                .get("/api/bessj/3/2.5")
            .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testBessjXZero() {
        given()
            .when()
                .get("/api/bessj/3/0")
            .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testBessjVerySmallX() {
        given()
            .when()
                .get("/api/bessj/5/0.0000000001")
            .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testFisherNormalCase() {
        given()
            .when()
                .get("/api/fisher/10/5/0.75")
            .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testFisherXZero() {
        given()
            .when()
                .get("/api/fisher/1/1/0.0")
            .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testFisherSmallMN() {
        given()
            .when()
                .get("/api/fisher/1/1/0.5")
            .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testRemainderNormalCase() {
        given()
            .when()
                .get("/api/remainder/17/5")
            .then()
                .statusCode(200)
                .body("resultAsInt", notNullValue());
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeA() {
        given()
            .when()
                .get("/api/remainder/-9/4")
            .then()
                .statusCode(200)
                .body("resultAsInt", notNullValue());
    }

    @Test(timeout = 60000)
    public void testRemainderZeroA() {
        given()
            .when()
                .get("/api/remainder/0/3")
            .then()
                .statusCode(200)
                .body("resultAsInt", notNullValue());
    }
}