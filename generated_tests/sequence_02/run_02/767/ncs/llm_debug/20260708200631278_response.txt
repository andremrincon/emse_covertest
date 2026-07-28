package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testBessjNLessThan2() {
        given()
            .pathParam("n", 1)
            .pathParam("x", 2.5)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjXZero() {
        given()
            .pathParam("n", 3)
            .pathParam("x", 0.0)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanN_AxLessThan8() {
        given()
            .pathParam("n", 2)
            .pathParam("x", 5.0)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanN_AxGreaterThanOrEqual8() {
        given()
            .pathParam("n", 2)
            .pathParam("x", 10.0)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjXNegativeNOdd_AxGreaterThanOrEqual8() {
        given()
            .pathParam("n", 3)
            .pathParam("x", -10.0)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxLessThanOrEqualN() {
        given()
            .pathParam("n", 5)
            .pathParam("x", 2.5)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjInvalidN() {
        given()
            .pathParam("n", "abc")
            .pathParam("x", 2.5)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(400);
    }
}