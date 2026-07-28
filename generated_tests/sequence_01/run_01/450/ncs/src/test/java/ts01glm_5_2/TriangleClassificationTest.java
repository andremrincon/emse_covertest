package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        given()
            .when()
                .get("/api/triangle/3/3/3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleMaxA() {
        given()
            .when()
                .get("/api/triangle/5/1/1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleMaxB() {
        given()
            .when()
                .get("/api/triangle/1/5/1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleMaxC() {
        given()
            .when()
                .get("/api/triangle/1/1/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        given()
            .when()
                .get("/api/triangle/3/3/4")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        given()
            .when()
                .get("/api/triangle/3/4/5")
            .then()
                .statusCode(200);
    }
}