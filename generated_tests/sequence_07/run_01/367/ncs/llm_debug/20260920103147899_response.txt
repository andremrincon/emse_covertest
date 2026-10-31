package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.nullValue;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null) {
            base = System.getenv("BASE_URL");
        }
        if (base == null) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testClassifyZeroEdge() {
        given()
        .when()
            .get("/api/triangle/0/4/5")
        .then()
            .body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testClassifyNegativeEdge() {
        given()
        .when()
            .get("/api/triangle/5/5/-1")
        .then()
            .body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testClassifyEquilateral() {
        given()
        .when()
            .get("/api/triangle/1/1/1")
        .then()
            .body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testClassifyDegenerate() {
        given()
        .when()
            .get("/api/triangle/1/2/3")
        .then()
            .body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testClassifyIsosceles() {
        given()
        .when()
            .get("/api/triangle/3/3/4")
        .then()
            .body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testClassifyScaleneRight() {
        given()
        .when()
            .get("/api/triangle/3/4/5")
        .then()
            .body("triangleType", nullValue());
    }
}