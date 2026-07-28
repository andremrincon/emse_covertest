package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null) {
            baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testInvalidSideA() {
        RestAssured.given()
                .when()
                .get("/api/triangle/0/1/1")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidSideB() {
        RestAssured.given()
                .when()
                .get("/api/triangle/1/0/1")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidSideC() {
        RestAssured.given()
                .when()
                .get("/api/triangle/1/1/0")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        RestAssured.given()
                .when()
                .get("/api/triangle/2/2/2")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        RestAssured.given()
                .when()
                .get("/api/triangle/2/2/3")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        RestAssured.given()
                .when()
                .get("/api/triangle/3/4/5")
                .then()
                .statusCode(200);
    }
}