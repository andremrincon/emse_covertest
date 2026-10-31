package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TriangleClassificationTest {

    private String getBaseUrl() {
        return System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testEquilateral() {
        int a = 3;
        int b = 3;
        int c = 3;

        given()
            .baseUri(getBaseUrl())
            .pathParam("a", a)
            .pathParam("b", b)
            .pathParam("c", c)
        .when()
            .get("/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidTriangle() {
        int a = 1;
        int b = 2;
        int c = 4;

        given()
            .baseUri(getBaseUrl())
            .pathParam("a", a)
            .pathParam("b", b)
            .pathParam("c", c)
        .when()
            .get("/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsosceles() {
        int a = 3;
        int b = 3;
        int c = 4;

        given()
            .baseUri(getBaseUrl())
            .pathParam("a", a)
            .pathParam("b", b)
            .pathParam("c", c)
        .when()
            .get("/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScalene() {
        int a = 3;
        int b = 4;
        int c = 5;

        given()
            .baseUri(getBaseUrl())
            .pathParam("a", a)
            .pathParam("b", b)
            .pathParam("c", c)
        .when()
            .get("/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testZeroSide() {
        int a = 0;
        int b = 3;
        int c = 4;

        given()
            .baseUri(getBaseUrl())
            .pathParam("a", a)
            .pathParam("b", b)
            .pathParam("c", c)
        .when()
            .get("/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }
}