package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class BessjTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testBessjNLessThan2Returns400() {
        given()
            .pathParam("n", 1)
            .pathParam("x", 2.5)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanN() {
        given()
            .pathParam("n", 3)
            .pathParam("x", 5.0)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxEqualsZero() {
        given()
            .pathParam("n", 3)
            .pathParam("x", 0.0)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjBackwardRecursion() {
        given()
            .pathParam("n", 3)
            .pathParam("x", 2.5)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNegativeXOddN() {
        given()
            .pathParam("n", 3)
            .pathParam("x", -5.0)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjLargeX() {
        given()
            .pathParam("n", 3)
            .pathParam("x", 10.0)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(200);
    }
}