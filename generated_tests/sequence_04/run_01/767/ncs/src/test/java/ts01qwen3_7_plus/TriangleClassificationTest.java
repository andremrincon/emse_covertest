package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testTriangleClassificationWithZeroSideA() {
        given()
            .when()
            .get("/api/triangle/0/4/5")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleClassificationWithZeroSideB() {
        given()
            .when()
            .get("/api/triangle/3/0/5")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleClassificationEquilateral() {
        given()
            .when()
            .get("/api/triangle/3/3/3")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleClassificationInvalidTriangle() {
        given()
            .when()
            .get("/api/triangle/1/1/5")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleClassificationIsosceles() {
        given()
            .when()
            .get("/api/triangle/3/3/4")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleClassificationScalene() {
        given()
            .when()
            .get("/api/triangle/3/4/6")
            .then()
            .statusCode(200);
    }
}