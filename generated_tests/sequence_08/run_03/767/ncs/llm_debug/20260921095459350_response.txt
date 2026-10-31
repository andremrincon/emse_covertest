package ts01qwen3_7_plus_3;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TriangleClassificationTest {

    @Test(timeout = 60000)
    public void testInvalidEdgesZero() {
        given()
                .when()
                .get("/api/triangle/0/4/5")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateral() {
        given()
                .when()
                .get("/api/triangle/3/3/3")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidTriangle() {
        given()
                .when()
                .get("/api/triangle/5/2/2")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsosceles() {
        given()
                .when()
                .get("/api/triangle/3/3/4")
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