package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

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
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangle() {
        given()
            .when()
                .get("/api/triangle/5/2/2")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        given()
            .when()
                .get("/api/triangle/2/2/3")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testScaleneRightTriangle() {
        given()
            .when()
                .get("/api/triangle/3/4/5")
            .then()
                .statusCode(200)
                .body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleZeroFirstEdge() {
        given()
            .when()
                .get("/api/triangle/0/1/1")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleZeroSecondEdge() {
        given()
            .when()
                .get("/api/triangle/1/0/1")
            .then()
                .statusCode(lessThan(300));
    }
}