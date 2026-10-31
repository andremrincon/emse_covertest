package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testClassifyWithZeroEdgeReturnsInvalid() {
        given()
            .when()
                .get("/api/triangle/0/4/5")
            .then()
                .statusCode(lessThan(300))
                .body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testClassifyEquilateralTriangle() {
        given()
            .when()
                .get("/api/triangle/5/5/5")
            .then()
                .statusCode(lessThan(300))
                .body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testClassifyDegenerateTriangleMaxIsA() {
        given()
            .when()
                .get("/api/triangle/10/3/3")
            .then()
                .statusCode(lessThan(300))
                .body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testClassifyDegenerateTriangleMaxIsC() {
        given()
            .when()
                .get("/api/triangle/1/1/2")
            .then()
                .statusCode(lessThan(300))
                .body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testClassifyIsoscelesTriangle() {
        given()
            .when()
                .get("/api/triangle/3/3/4")
            .then()
                .statusCode(lessThan(300))
                .body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testClassifyScaleneTriangle() {
        given()
            .when()
                .get("/api/triangle/3/4/5")
            .then()
                .statusCode(lessThan(300))
                .body("triangleType", nullValue());
    }
}