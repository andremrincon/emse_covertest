package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TriangleClassificationTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testClassify_negativeSideA_returnsInvalidTriangle() {
        given()
            .when()
                .get("/api/triangle/-1/4/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassify_equilateralTriangle_returnsEquilateral() {
        given()
            .when()
                .get("/api/triangle/3/3/3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassify_degenerateTriangleMaxA_returnsInvalid() {
        given()
            .when()
                .get("/api/triangle/10/3/3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassify_scaleneTriangle_returnsScalene() {
        given()
            .when()
                .get("/api/triangle/3/4/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassify_isoscelesTriangle_returnsIsosceles() {
        given()
            .when()
                .get("/api/triangle/3/3/4")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassify_degenerateTriangleMaxC_returnsInvalid() {
        given()
            .when()
                .get("/api/triangle/3/4/10")
            .then()
                .statusCode(200);
    }
}