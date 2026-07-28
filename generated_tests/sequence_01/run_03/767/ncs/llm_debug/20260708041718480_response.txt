package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class TriangleClassificationTest {

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
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
    public void testInvalidTriangle() {
        given()
            .pathParam("a", 1)
            .pathParam("b", 1)
            .pathParam("c", 3)
        .when()
            .get("/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        given()
            .pathParam("a", 2)
            .pathParam("b", 2)
            .pathParam("c", 3)
        .when()
            .get("/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        given()
            .pathParam("a", 3)
            .pathParam("b", 4)
            .pathParam("c", 5)
        .when()
            .get("/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidSides() {
        given()
            .pathParam("a", 0)
            .pathParam("b", 4)
            .pathParam("c", 5)
        .when()
            .get("/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }
}