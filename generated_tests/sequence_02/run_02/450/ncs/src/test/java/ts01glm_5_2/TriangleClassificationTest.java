package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleWithZeroSide() {
        given()
            .when()
                .get("/api/triangle/{a}/{b}/{c}", 0, 4, 5)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        given()
            .when()
                .get("/api/triangle/{a}/{b}/{c}", 3, 3, 3)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleMaxIsA() {
        given()
            .when()
                .get("/api/triangle/{a}/{b}/{c}", 5, 2, 3)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleMaxIsC() {
        given()
            .when()
                .get("/api/triangle/{a}/{b}/{c}", 2, 3, 5)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        given()
            .when()
                .get("/api/triangle/{a}/{b}/{c}", 3, 3, 4)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        given()
            .when()
                .get("/api/triangle/{a}/{b}/{c}", 3, 4, 5)
            .then()
                .statusCode(200);
    }
}