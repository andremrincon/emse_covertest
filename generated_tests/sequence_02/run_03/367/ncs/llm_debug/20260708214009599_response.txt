package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

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
    public void testBessjAxEqualToZero() {
        given()
            .pathParam("n", 3)
            .pathParam("x", 0.0)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanNWithLargeX() {
        given()
            .pathParam("n", 3)
            .pathParam("x", 10.0)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testBessjNegativeXLargeAxOddN() {
        given()
            .pathParam("n", 3)
            .pathParam("x", -10.0)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testBessjAxLessThanOrEqualN() {
        given()
            .pathParam("n", 10)
            .pathParam("x", 5.0)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testBessjNegativeXLargeAxEvenN() {
        given()
            .pathParam("n", 4)
            .pathParam("x", -10.0)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testBessjInvalidNType() {
        given()
            .pathParam("n", "abc")
            .pathParam("x", 2.5)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjNZeroThrowsException() {
        given()
            .pathParam("n", 0)
            .pathParam("x", 2.5)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanNWithSmallX() {
        given()
            .pathParam("n", 2)
            .pathParam("x", 5.0)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(400);
    }
}