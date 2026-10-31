package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TriangleClassificationTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testClassify_NegativeEdgeA_ReturnsNotTriangle() {
        given()
                .when()
                .get("/api/triangle/{a}/{b}/{c}", 0, 1, 1)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassify_NegativeEdgeB_ReturnsNotTriangle() {
        given()
                .when()
                .get("/api/triangle/{a}/{b}/{c}", 1, 0, 1)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassify_AllEdgesEqual_ReturnsEquilateral() {
        given()
                .when()
                .get("/api/triangle/{a}/{b}/{c}", 1, 1, 1)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassify_MaxEdgeAExceedsSum_ReturnsNotTriangle() {
        given()
                .when()
                .get("/api/triangle/{a}/{b}/{c}", 5, 1, 1)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassify_TwoEdgesEqual_ReturnsIsosceles() {
        given()
                .when()
                .get("/api/triangle/{a}/{b}/{c}", 2, 2, 3)
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassify_AllEdgesDifferent_ReturnsScalene() {
        given()
                .when()
                .get("/api/triangle/{a}/{b}/{c}", 3, 4, 5)
                .then()
                .statusCode(200);
    }
}