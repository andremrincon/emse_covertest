package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

public class TriangleClassificationTest {

    @Test(timeout = 60000)
    public void testInvalidTriangleZeroSide() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
        RestAssured.given().when().get("/api/triangle/0/4/5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
        RestAssured.given().when().get("/api/triangle/3/3/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleInequalityC() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
        RestAssured.given().when().get("/api/triangle/1/1/5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleInequalityA() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
        RestAssured.given().when().get("/api/triangle/5/1/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
        RestAssured.given().when().get("/api/triangle/3/3/4").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
        RestAssured.given().when().get("/api/triangle/3/4/5").then().statusCode(200);
    }
}