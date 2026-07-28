package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

public class TriangleClassificationTest {

    @Test(timeout = 60000)
    public void testInvalidTriangle() {
        RestAssured.given()
                .when()
                .get("/api/triangle/0/1/1")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        RestAssured.given()
                .when()
                .get("/api/triangle/2/2/2")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotATriangle() {
        RestAssured.given()
                .when()
                .get("/api/triangle/5/1/1")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangleAB() {
        RestAssured.given()
                .when()
                .get("/api/triangle/2/2/3")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangleBC() {
        RestAssured.given()
                .when()
                .get("/api/triangle/3/2/2")
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