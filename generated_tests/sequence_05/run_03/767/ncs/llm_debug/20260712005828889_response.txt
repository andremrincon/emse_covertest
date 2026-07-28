package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

public class TriangleClassificationTest {

    @Test(timeout = 60000)
    public void testInvalidTriangleZeroSide() {
        RestAssured.given()
                .when()
                .get("/api/triangle/0/4/5")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        RestAssured.given()
                .when()
                .get("/api/triangle/3/3/3")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotATriangle() {
        RestAssured.given()
                .when()
                .get("/api/triangle/1/2/4")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        RestAssured.given()
                .when()
                .get("/api/triangle/3/3/4")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        RestAssured.given()
                .when()
                .get("/api/triangle/3/4/5")
                .then()
                .statusCode(200);
    }
}