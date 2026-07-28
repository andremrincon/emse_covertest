package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ExpintTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testExpintInvalidN() {
        given()
        .when()
            .get("/api/expint/-1/1.0")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintNZero() {
        given()
        .when()
            .get("/api/expint/0/1.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXZero() {
        given()
        .when()
            .get("/api/expint/2/0.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXGreaterThanOne() {
        given()
        .when()
            .get("/api/expint/1/2.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXLessThanOneN1() {
        given()
        .when()
            .get("/api/expint/1/0.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXLessThanOneN2() {
        given()
        .when()
            .get("/api/expint/2/0.5")
        .then()
            .statusCode(200);
    }
}