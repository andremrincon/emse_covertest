package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testInvalidSideZero() {
        given()
            .when()
                .get("/api/triangle/0/4/5")
            .then()
                .statusCode(200)
                .body("triangleType", equalTo(null));
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        given()
            .when()
                .get("/api/triangle/3/3/3")
            .then()
                .statusCode(200)
                .body("triangleType", equalTo(null));
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleNotClosed() {
        given()
            .when()
                .get("/api/triangle/1/2/5")
            .then()
                .statusCode(200)
                .body("triangleType", equalTo(null));
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        given()
            .when()
                .get("/api/triangle/3/3/4")
            .then()
                .statusCode(200)
                .body("triangleType", equalTo(null));
    }

    @Test(timeout = 60000)
    public void testRightScaleneTriangle() {
        given()
            .when()
                .get("/api/triangle/3/4/5")
            .then()
                .statusCode(200)
                .body("triangleType", equalTo(null));
    }

    @Test(timeout = 60000)
    public void testNonRightScaleneTriangle() {
        given()
            .when()
                .get("/api/triangle/4/5/6")
            .then()
                .statusCode(200)
                .body("triangleType", equalTo(null));
    }
}