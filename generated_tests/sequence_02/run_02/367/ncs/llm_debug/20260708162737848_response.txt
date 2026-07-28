package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

public class TriangleClassificationTest {

    @Test(timeout = 60000)
    public void testInvalidTriangleZeroSide() {
        RestAssured.given()
                .when()
                .get("http://localhost:8080/api/triangle/0/1/1")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        RestAssured.given()
                .when()
                .get("http://localhost:8080/api/triangle/1/1/1")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleSumOfSides() {
        RestAssured.given()
                .when()
                .get("http://localhost:8080/api/triangle/5/2/2")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        RestAssured.given()
                .when()
                .get("http://localhost:8080/api/triangle/2/2/3")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        RestAssured.given()
                .when()
                .get("http://localhost:8080/api/triangle/3/4/5")
                .then()
                .statusCode(200);
    }
}