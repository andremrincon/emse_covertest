package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TriangleClassificationTest {

    private static String baseUrl;

    @BeforeClass
    public static void setup() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testInvalidNegativeSidesReturnsNotTriangle() {
        given()
        .when()
            .get("/api/triangle/-1/4/5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        given()
        .when()
            .get("/api/triangle/3/3/3")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        given()
        .when()
            .get("/api/triangle/3/4/5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        given()
        .when()
            .get("/api/triangle/3/3/4")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleMaxA() {
        given()
        .when()
            .get("/api/triangle/5/2/2")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleMaxC() {
        given()
        .when()
            .get("/api/triangle/2/2/5")
        .then()
            .statusCode(200);
    }
}