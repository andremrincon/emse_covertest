package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TriangleClassificationTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testClassify_FirstEdgeZero_ReturnsNotTriangle() {
        given()
            .when()
                .get("/api/triangle/0/1/1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassify_SecondEdgeZero_ReturnsNotTriangle() {
        given()
            .when()
                .get("/api/triangle/1/0/1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassify_EquilateralTriangle_ReturnsEquilateral() {
        given()
            .when()
                .get("/api/triangle/3/3/3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassify_DegenerateTriangle_ReturnsNotTriangle() {
        given()
            .when()
                .get("/api/triangle/5/2/2")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassify_IsoscelesTriangle_ReturnsIsosceles() {
        given()
            .when()
                .get("/api/triangle/3/3/4")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassify_ScaleneTriangle_ReturnsScalene() {
        given()
            .when()
                .get("/api/triangle/3/4/5")
            .then()
                .statusCode(200);
    }
}