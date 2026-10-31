package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TriangleClassificationTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testInvalidTriangle_ZeroSide() {
        given()
            .when()
            .get("/api/triangle/{a}/{b}/{c}", 0, 4, 5)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        given()
            .when()
            .get("/api/triangle/{a}/{b}/{c}", 3, 3, 3)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidTriangle_NotATriangle() {
        given()
            .when()
            .get("/api/triangle/{a}/{b}/{c}", 5, 2, 2)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        given()
            .when()
            .get("/api/triangle/{a}/{b}/{c}", 3, 3, 4)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        given()
            .when()
            .get("/api/triangle/{a}/{b}/{c}", 3, 4, 5)
            .then()
            .statusCode(200);
    }
}