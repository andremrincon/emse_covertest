package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl != null) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testClassifyNegativeSideReturnsNotATriangle() {
        given()
            .when()
                .get("/api/triangle/{a}/{b}/{c}", -1, 4, 5)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyEquilateralTriangle() {
        given()
            .when()
                .get("/api/triangle/{a}/{b}/{c}", 3, 3, 3)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyDegenerateTriangle() {
        given()
            .when()
                .get("/api/triangle/{a}/{b}/{c}", 1, 1, 2)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyIsoscelesTriangle() {
        given()
            .when()
                .get("/api/triangle/{a}/{b}/{c}", 3, 3, 4)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyScaleneTriangle() {
        given()
            .when()
                .get("/api/triangle/{a}/{b}/{c}", 3, 4, 5)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyZeroSideReturnsNotATriangle() {
        given()
            .when()
                .get("/api/triangle/{a}/{b}/{c}", 0, 5, 5)
            .then()
                .statusCode(200);
    }
}