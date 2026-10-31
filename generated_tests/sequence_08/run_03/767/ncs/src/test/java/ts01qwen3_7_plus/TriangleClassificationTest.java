package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TriangleClassificationTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testEquilateral() {
        given()
            .when()
                .get("/api/triangle/2/2/2")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidMaxA() {
        given()
            .when()
                .get("/api/triangle/3/1/1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidMaxB() {
        given()
            .when()
                .get("/api/triangle/1/3/1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidMaxC() {
        given()
            .when()
                .get("/api/triangle/1/1/3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsosceles() {
        given()
            .when()
                .get("/api/triangle/2/2/3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScalene() {
        given()
            .when()
                .get("/api/triangle/3/4/5")
            .then()
                .statusCode(200);
    }
}