package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TriangleClassificationTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testInvalidEdgeZero() {
        given()
            .pathParam("a", 0)
            .pathParam("b", 1)
            .pathParam("c", 1)
            .when()
                .get("/api/triangle/{a}/{b}/{c}")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateral() {
        given()
            .pathParam("a", 2)
            .pathParam("b", 2)
            .pathParam("c", 2)
            .when()
                .get("/api/triangle/{a}/{b}/{c}")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidTriangle() {
        given()
            .pathParam("a", 1)
            .pathParam("b", 1)
            .pathParam("c", 3)
            .when()
                .get("/api/triangle/{a}/{b}/{c}")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsosceles() {
        given()
            .pathParam("a", 2)
            .pathParam("b", 2)
            .pathParam("c", 3)
            .when()
                .get("/api/triangle/{a}/{b}/{c}")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScalene() {
        given()
            .pathParam("a", 3)
            .pathParam("b", 4)
            .pathParam("c", 5)
            .when()
                .get("/api/triangle/{a}/{b}/{c}")
            .then()
                .statusCode(200);
    }
}