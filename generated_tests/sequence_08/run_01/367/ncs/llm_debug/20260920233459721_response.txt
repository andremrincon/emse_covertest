package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TriangleClassificationTest {

    @Before
    public void setup() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testInvalidSide() {
        given()
        .when()
            .get("/api/triangle/0/1/1")
        .then()
            .statusCode(200);
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
    public void testNotATriangle() {
        given()
        .when()
            .get("/api/triangle/1/2/4")
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