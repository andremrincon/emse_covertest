package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

public class TriangleClassificationTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testClassify_InvalidSides_A() {
        int a = 0;
        int b = 1;
        int c = 1;

        RestAssured.given()
                .pathParam("a", a)
                .pathParam("b", b)
                .pathParam("c", c)
                .when()
                .get(baseUrl + "/api/triangle/{a}/{b}/{c}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassify_InvalidSides_B() {
        int a = 1;
        int b = 0;
        int c = 1;

        RestAssured.given()
                .pathParam("a", a)
                .pathParam("b", b)
                .pathParam("c", c)
                .when()
                .get(baseUrl + "/api/triangle/{a}/{b}/{c}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassify_Equilateral() {
        int a = 2;
        int b = 2;
        int c = 2;

        RestAssured.given()
                .pathParam("a", a)
                .pathParam("b", b)
                .pathParam("c", c)
                .when()
                .get(baseUrl + "/api/triangle/{a}/{b}/{c}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassify_InvalidTriangle() {
        int a = 1;
        int b = 1;
        int c = 5;

        RestAssured.given()
                .pathParam("a", a)
                .pathParam("b", b)
                .pathParam("c", c)
                .when()
                .get(baseUrl + "/api/triangle/{a}/{b}/{c}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassify_Isosceles() {
        int a = 2;
        int b = 2;
        int c = 3;

        RestAssured.given()
                .pathParam("a", a)
                .pathParam("b", b)
                .pathParam("c", c)
                .when()
                .get(baseUrl + "/api/triangle/{a}/{b}/{c}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassify_Scalene() {
        int a = 3;
        int b = 4;
        int c = 5;

        RestAssured.given()
                .pathParam("a", a)
                .pathParam("b", b)
                .pathParam("c", c)
                .when()
                .get(baseUrl + "/api/triangle/{a}/{b}/{c}")
                .then()
                .statusCode(200);
    }
}