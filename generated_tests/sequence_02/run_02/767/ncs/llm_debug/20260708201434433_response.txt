package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

public class BessjTest {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testBessjNLessThan2ThrowsException() {
        given()
            .pathParam("n", 1)
            .pathParam("x", 2.5)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjAxLessThanNWithSmallX() {
        given()
            .pathParam("n", 3)
            .pathParam("x", 2.5)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanNWithLargeX() {
        given()
            .pathParam("n", 3)
            .pathParam("x", 10.0)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNegativeXOddNReturnsNegativeAns() {
        given()
            .pathParam("n", 3)
            .pathParam("x", -10.0)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNegativeXEvenNReturnsPositiveAns() {
        given()
            .pathParam("n", 4)
            .pathParam("x", -10.0)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjXZeroReturnsZero() {
        given()
            .pathParam("n", 3)
            .pathParam("x", 0)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjBoundaryN2WithSmallX() {
        given()
            .pathParam("n", 2)
            .pathParam("x", 2.5)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjNegativeSmallXOddNElseBranch() {
        given()
            .pathParam("n", 3)
            .pathParam("x", -2.5)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjInvalidNTypeReturns400() {
        given()
            .pathParam("n", "abc")
            .pathParam("x", 2.5)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(400);
    }
}