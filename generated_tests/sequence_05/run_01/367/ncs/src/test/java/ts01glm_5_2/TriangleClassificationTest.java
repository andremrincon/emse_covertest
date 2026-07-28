package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class TriangleClassificationTest {

    private static final String BASE_URL = System.getProperty("server.url", "http://localhost:8080");

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testClassifyWithFirstSideZero() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/triangle/0/4/5")
        .then()
            .statusCode(200)
            .body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testClassifyWithThirdSideZero() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/triangle/1/1/0")
        .then()
            .statusCode(200)
            .body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testClassifyEquilateralTriangle() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/triangle/3/3/3")
        .then()
            .statusCode(200)
            .body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testClassifyInvalidTriangleWithMaxSideTooLarge() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/triangle/10/1/1")
        .then()
            .statusCode(200)
            .body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testClassifyIsoscelesTriangle() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/triangle/3/3/4")
        .then()
            .statusCode(200)
            .body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testClassifyScaleneTriangle() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/triangle/3/4/5")
        .then()
            .statusCode(200)
            .body("triangleType", nullValue());
    }
}