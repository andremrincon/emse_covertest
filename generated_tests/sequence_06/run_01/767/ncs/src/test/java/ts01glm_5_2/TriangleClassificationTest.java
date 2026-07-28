package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TriangleClassificationTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testClassifyWithZeroEdge() {
        given()
            .when()
                .get("/api/triangle/0/4/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyWithNegativeEdge() {
        given()
            .when()
                .get("/api/triangle/3/-1/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyEquilateralTriangle() {
        given()
            .when()
                .get("/api/triangle/3/3/3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyTriangleInequalityViolation() {
        given()
            .when()
                .get("/api/triangle/5/1/2")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyIsoscelesTriangle() {
        given()
            .when()
                .get("/api/triangle/3/3/4")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyScaleneTriangle() {
        given()
            .when()
                .get("/api/triangle/3/4/5")
            .then()
                .statusCode(200);
    }
}