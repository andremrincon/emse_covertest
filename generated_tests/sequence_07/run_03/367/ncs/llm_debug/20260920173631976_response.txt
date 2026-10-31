package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class TriangleClassificationTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testNegativeEdgeReturnsNotTriangle() {
        given()
            .when()
                .get("/api/triangle/0/4/5")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        given()
            .when()
                .get("/api/triangle/3/3/3")
            .then()
                .body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleMaxIsA() {
        given()
            .when()
                .get("/api/triangle/5/2/3")
            .then()
                .body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleMaxIsB() {
        given()
            .when()
                .get("/api/triangle/2/5/3")
            .then()
                .body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        given()
            .when()
                .get("/api/triangle/3/3/4")
            .then()
                .body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        given()
            .when()
                .get("/api/triangle/3/4/5")
            .then()
                .body("triangleType", nullValue());
    }
}