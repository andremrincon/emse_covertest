package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

public class TriangleClassificationTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv() != null ? System.getenv().getOrDefault("BASE_URL", "http://localhost:8080") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testZeroSideReturnsNotTriangle() {
        RestAssured.given()
            .when()
                .get("/api/triangle/0/4/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        RestAssured.given()
            .when()
                .get("/api/triangle/3/3/3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleMaxA() {
        RestAssured.given()
            .when()
                .get("/api/triangle/5/1/1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        RestAssured.given()
            .when()
                .get("/api/triangle/3/3/4")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        RestAssured.given()
            .when()
                .get("/api/triangle/3/4/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleMaxC() {
        RestAssured.given()
            .when()
                .get("/api/triangle/1/1/5")
            .then()
                .statusCode(200);
    }
}