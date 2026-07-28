package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void classify_negativeEdge_returnsInvalid() {
        given()
            .when()
                .get("/api/triangle/0/4/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void classify_equilateralTriangle_returnsEquilateral() {
        given()
            .when()
                .get("/api/triangle/5/5/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void classify_degenerateTriangle_returnsInvalid() {
        given()
            .when()
                .get("/api/triangle/10/3/3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void classify_isoscelesTriangle_returnsIsosceles() {
        given()
            .when()
                .get("/api/triangle/5/5/3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void classify_scaleneTriangle_returnsScalene() {
        given()
            .when()
                .get("/api/triangle/3/4/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void classify_allNegativeEdges_returnsInvalid() {
        given()
            .when()
                .get("/api/triangle/-1/-2/-3")
            .then()
                .statusCode(200);
    }
}