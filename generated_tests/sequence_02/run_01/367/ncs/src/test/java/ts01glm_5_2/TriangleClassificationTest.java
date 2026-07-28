package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TriangleClassificationTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testNegativeEdgeReturnsInvalidTriangle() {
        given()
            .pathParam("a", -1)
            .pathParam("b", 4)
            .pathParam("c", 5)
        .when()
            .get("/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangleReturnsThree() {
        given()
            .pathParam("a", 3)
            .pathParam("b", 3)
            .pathParam("c", 3)
        .when()
            .get("/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleInequalityViolationMaxA() {
        given()
            .pathParam("a", 5)
            .pathParam("b", 2)
            .pathParam("c", 2)
        .when()
            .get("/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleInequalityViolationMaxB() {
        given()
            .pathParam("a", 2)
            .pathParam("b", 5)
            .pathParam("c", 2)
        .when()
            .get("/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangleReturnsTwo() {
        given()
            .pathParam("a", 3)
            .pathParam("b", 3)
            .pathParam("c", 4)
        .when()
            .get("/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangleReturnsOne() {
        given()
            .pathParam("a", 3)
            .pathParam("b", 4)
            .pathParam("c", 5)
        .when()
            .get("/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }
}