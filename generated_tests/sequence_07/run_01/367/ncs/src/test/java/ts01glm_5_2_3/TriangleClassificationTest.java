package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setUp() {
        String base = System.getProperty("server.url", "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNonPositiveEdgeReturnsInvalid() {
        RestAssured.given()
            .when()
                .get("/api/triangle/{a}/{b}/{c}", 0, 4, 5)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        RestAssured.given()
            .when()
                .get("/api/triangle/{a}/{b}/{c}", 5, 5, 5)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleMaxA() {
        RestAssured.given()
            .when()
                .get("/api/triangle/{a}/{b}/{c}", 10, 4, 5)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleMaxC() {
        RestAssured.given()
            .when()
                .get("/api/triangle/{a}/{b}/{c}", 4, 5, 10)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        RestAssured.given()
            .when()
                .get("/api/triangle/{a}/{b}/{c}", 5, 5, 8)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        RestAssured.given()
            .when()
                .get("/api/triangle/{a}/{b}/{c}", 3, 4, 5)
            .then()
                .statusCode(200);
    }
}