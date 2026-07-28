package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

public class TriangleClassificationTest {

    static {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleZeroSide() {
        RestAssured.given()
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
        RestAssured.given()
                .pathParam("a", 2)
                .pathParam("b", 2)
                .pathParam("c", 2)
                .when()
                .get("/api/triangle/{a}/{b}/{c}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotATriangle() {
        RestAssured.given()
                .pathParam("a", 1)
                .pathParam("b", 2)
                .pathParam("c", 3)
                .when()
                .get("/api/triangle/{a}/{b}/{c}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        RestAssured.given()
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