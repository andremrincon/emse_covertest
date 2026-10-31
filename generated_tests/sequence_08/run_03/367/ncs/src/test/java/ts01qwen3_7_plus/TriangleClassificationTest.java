package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

public class TriangleClassificationTest {

    @Test(timeout = 60000)
    public void testInvalidEdge() {
        RestAssured.given()
                .pathParam("a", 0)
                .pathParam("b", 4)
                .pathParam("c", 5)
                .when()
                .get("/api/triangle/{a}/{b}/{c}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        RestAssured.given()
                .pathParam("a", 3)
                .pathParam("b", 3)
                .pathParam("c", 3)
                .when()
                .get("/api/triangle/{a}/{b}/{c}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidTriangle() {
        RestAssured.given()
                .pathParam("a", 3)
                .pathParam("b", 1)
                .pathParam("c", 2)
                .when()
                .get("/api/triangle/{a}/{b}/{c}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        RestAssured.given()
                .pathParam("a", 3)
                .pathParam("b", 3)
                .pathParam("c", 4)
                .when()
                .get("/api/triangle/{a}/{b}/{c}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        RestAssured.given()
                .pathParam("a", 3)
                .pathParam("b", 4)
                .pathParam("c", 5)
                .when()
                .get("/api/triangle/{a}/{b}/{c}")
                .then()
                .statusCode(200);
    }
}