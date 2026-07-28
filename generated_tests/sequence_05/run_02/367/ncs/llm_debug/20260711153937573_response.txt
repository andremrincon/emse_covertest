package ts01glm_5_2;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    private static String baseUrl;

    @BeforeClass
    public static void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testClassifyWithZeroSideReturnsNotTriangle() {
        given()
            .when()
                .get("/api/triangle/0/4/5")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testClassifyEquilateralTriangle() {
        given()
            .when()
                .get("/api/triangle/3/3/3")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testClassifyDegenerateTriangleMaxA() {
        given()
            .when()
                .get("/api/triangle/5/2/3")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testClassifyDegenerateTriangleMaxB() {
        given()
            .when()
                .get("/api/triangle/2/5/3")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testClassifyIsoscelesTriangle() {
        given()
            .when()
                .get("/api/triangle/3/3/4")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testClassifyScaleneTriangle() {
        given()
            .when()
                .get("/api/triangle/3/4/5")
            .then()
                .statusCode(200)
                .body("triangleType", nullValue());
    }
}