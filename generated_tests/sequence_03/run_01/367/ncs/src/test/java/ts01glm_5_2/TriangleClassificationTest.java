package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

public class TriangleClassificationTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testClassifyWithZeroEdge() {
        RestAssured.given()
            .baseUri(baseUrl)
        .when()
            .get("/api/triangle/0/5/5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyEquilateralTriangle() {
        RestAssured.given()
            .baseUri(baseUrl)
        .when()
            .get("/api/triangle/5/5/5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyInvalidTriangleMaxIsA() {
        RestAssured.given()
            .baseUri(baseUrl)
        .when()
            .get("/api/triangle/10/3/4")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyInvalidTriangleMaxIsB() {
        RestAssured.given()
            .baseUri(baseUrl)
        .when()
            .get("/api/triangle/3/10/4")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyIsoscelesTriangle() {
        RestAssured.given()
            .baseUri(baseUrl)
        .when()
            .get("/api/triangle/3/3/4")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyScaleneTriangle() {
        RestAssured.given()
            .baseUri(baseUrl)
        .when()
            .get("/api/triangle/3/4/5")
        .then()
            .statusCode(200);
    }
}