package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleWithZeroSide() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/triangle/0/4/5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleMaxIsA() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/triangle/5/2/3")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleMaxIsB() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/triangle/2/5/3")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/triangle/3/3/4")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/triangle/3/3/3")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/triangle/3/4/5")
        .then()
            .statusCode(200);
    }
}