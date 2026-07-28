package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TriangleClassificationTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.trim().isEmpty()) {
            baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testTriangleClassification_AZero() {
        given()
            .when()
                .get("/api/triangle/0/1/1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleClassification_BZero() {
        given()
            .when()
                .get("/api/triangle/1/0/1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleClassification_CZero() {
        given()
            .when()
                .get("/api/triangle/1/1/0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleClassification_Equilateral() {
        given()
            .when()
                .get("/api/triangle/2/2/2")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleClassification_InvalidTriangle() {
        given()
            .when()
                .get("/api/triangle/1/2/4")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleClassification_Scalene() {
        given()
            .when()
                .get("/api/triangle/3/4/5")
            .then()
                .statusCode(200);
    }
}