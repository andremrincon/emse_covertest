package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TriangleClassificationTest {

    @Before
    public void setup() {
        String envUrl = System.getenv("BASE_URL");
        String baseUrl = System.getProperty("baseUrl", envUrl != null ? envUrl : "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        Response response = given()
                .when()
                .get("/api/triangle/3/3/3");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleInequality() {
        Response response = given()
                .when()
                .get("/api/triangle/1/2/5");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        Response response = given()
                .when()
                .get("/api/triangle/3/3/4");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        Response response = given()
                .when()
                .get("/api/triangle/3/4/5");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testZeroSideTriangle() {
        Response response = given()
                .when()
                .get("/api/triangle/0/3/4");
        response.then().statusCode(200);
    }
}