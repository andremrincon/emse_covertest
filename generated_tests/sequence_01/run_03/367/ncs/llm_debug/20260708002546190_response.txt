package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseURI", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleZeroA() {
        RestAssured
            .given()
                .pathParam("a", 0)
                .pathParam("b", 1)
                .pathParam("c", 1)
            .when()
                .get("/api/triangle/{a}/{b}/{c}")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        RestAssured
            .given()
                .pathParam("a", 1)
                .pathParam("b", 1)
                .pathParam("c", 1)
            .when()
                .get("/api/triangle/{a}/{b}/{c}")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotATriangleMaxSide() {
        RestAssured
            .given()
                .pathParam("a", 3)
                .pathParam("b", 1)
                .pathParam("c", 1)
            .when()
                .get("/api/triangle/{a}/{b}/{c}")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        RestAssured
            .given()
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
        RestAssured
            .given()
                .pathParam("a", 3)
                .pathParam("b", 4)
                .pathParam("c", 5)
            .when()
                .get("/api/triangle/{a}/{b}/{c}")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleZeroC() {
        RestAssured
            .given()
                .pathParam("a", 1)
                .pathParam("b", 1)
                .pathParam("c", 0)
            .when()
                .get("/api/triangle/{a}/{b}/{c}")
            .then()
                .statusCode(200);
    }
}