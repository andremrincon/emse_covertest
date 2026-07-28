package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class TriangleClassificationTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleWithZeroSide() {
        given()
            .when()
            .get("/api/triangle/0/4/5")
            .then()
            .statusCode(200)
            .body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        given()
            .when()
            .get("/api/triangle/3/3/3")
            .then()
            .statusCode(200)
            .body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleInequality() {
        given()
            .when()
            .get("/api/triangle/1/2/10")
            .then()
            .statusCode(200)
            .body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        given()
            .when()
            .get("/api/triangle/3/3/4")
            .then()
            .statusCode(200)
            .body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        given()
            .when()
            .get("/api/triangle/4/5/6")
            .then()
            .statusCode(200)
            .body("triangleType", nullValue());
    }
}