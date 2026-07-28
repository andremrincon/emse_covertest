package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TriangleClassificationTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleZeroSide() {
        given().when().get("/api/triangle/0/1/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        given().when().get("/api/triangle/3/3/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleMaxSide() {
        given().when().get("/api/triangle/1/2/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        given().when().get("/api/triangle/3/3/4").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(200);
    }
}