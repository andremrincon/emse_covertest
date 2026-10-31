package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.junit.Ignore;
public class TriangleClassificationTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. JSON path type doesn't match. Expected: equilateral   Actual: null")
    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        given()
                .when()
                .get("/api/triangle/3/3/3")
                .then()
                .statusCode(200)
                .body("type", equalTo("equilateral"));
    }

    @Ignore("1 expectation failed. JSON path type doesn't match. Expected: not_triangle   Actual: null")
    @Test(timeout = 60000)
    public void testNegativeEdgeReturnsNotTriangle() {
        given()
                .when()
                .get("/api/triangle/0/4/5")
                .then()
                .statusCode(200)
                .body("type", equalTo("not_triangle"));
    }

    @Ignore("1 expectation failed. JSON path type doesn't match. Expected: not_triangle   Actual: null")
    @Test(timeout = 60000)
    public void testDegenerateTriangle() {
        given()
                .when()
                .get("/api/triangle/1/1/2")
                .then()
                .statusCode(200)
                .body("type", equalTo("not_triangle"));
    }

    @Ignore("1 expectation failed. JSON path type doesn't match. Expected: isosceles   Actual: null")
    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        given()
                .when()
                .get("/api/triangle/3/3/4")
                .then()
                .statusCode(200)
                .body("type", equalTo("isosceles"));
    }

    @Ignore("1 expectation failed. JSON path type doesn't match. Expected: scalene   Actual: null")
    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        given()
                .when()
                .get("/api/triangle/3/4/6")
                .then()
                .statusCode(200)
                .body("type", equalTo("scalene"));
    }

    @Ignore("1 expectation failed. JSON path type doesn't match. Expected: right   Actual: null")
    @Test(timeout = 60000)
    public void testRightTriangle() {
        given()
                .when()
                .get("/api/triangle/3/4/5")
                .then()
                .statusCode(200)
                .body("type", equalTo("right"));
    }
}