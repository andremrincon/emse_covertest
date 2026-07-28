package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

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
    public void testClassifyWithZeroOrNegativeSide() {
        given()
            .when()
                .get("/api/triangle/0/4/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyEquilateral() {
        given()
            .when()
                .get("/api/triangle/3/3/3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyInvalidTriangle() {
        given()
            .when()
                .get("/api/triangle/1/1/3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyIsosceles() {
        given()
            .when()
                .get("/api/triangle/3/3/4")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassifyScalene() {
        given()
            .when()
                .get("/api/triangle/3/4/5")
            .then()
                .statusCode(200);
    }
}