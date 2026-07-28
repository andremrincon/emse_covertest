package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TriangleClassificationTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
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
    public void testDegenerateTriangleMaxA() {
        given()
            .when()
                .get("/api/triangle/5/1/1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleMaxB() {
        given()
            .when()
                .get("/api/triangle/1/5/1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleMaxC() {
        given()
            .when()
                .get("/api/triangle/1/1/5")
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
    public void testScaleneTriangle() {
        given()
            .when()
                .get("/api/triangle/3/4/5")
            .then()
                .statusCode(200);
    }
}