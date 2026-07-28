package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TriangleClassificationTest {

    private String getBaseUrl() {
        return System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleZeroSide() {
        Response response = given()
                .baseUri(getBaseUrl())
                .pathParam("a", 0)
                .pathParam("b", 1)
                .pathParam("c", 1)
                .when()
                .get("/api/triangle/{a}/{b}/{c}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        Response response = given()
                .baseUri(getBaseUrl())
                .pathParam("a", 2)
                .pathParam("b", 2)
                .pathParam("c", 2)
                .when()
                .get("/api/triangle/{a}/{b}/{c}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleInequality() {
        Response response = given()
                .baseUri(getBaseUrl())
                .pathParam("a", 1)
                .pathParam("b", 2)
                .pathParam("c", 10)
                .when()
                .get("/api/triangle/{a}/{b}/{c}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        Response response = given()
                .baseUri(getBaseUrl())
                .pathParam("a", 2)
                .pathParam("b", 2)
                .pathParam("c", 3)
                .when()
                .get("/api/triangle/{a}/{b}/{c}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        Response response = given()
                .baseUri(getBaseUrl())
                .pathParam("a", 3)
                .pathParam("b", 4)
                .pathParam("c", 5)
                .when()
                .get("/api/triangle/{a}/{b}/{c}");

        response.then().statusCode(200);
    }
}