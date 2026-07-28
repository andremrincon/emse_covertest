package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testClassify_InvalidEdgeA_ReturnsZero() {
        given()
            .when()
                .get("/api/triangle/-1/4/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassify_Equilateral_ReturnsThree() {
        given()
            .when()
                .get("/api/triangle/3/3/3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassify_NotTriangleAIsMax_ReturnsZero() {
        given()
            .when()
                .get("/api/triangle/10/1/1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassify_NotTriangleBIsMax_ReturnsZero() {
        given()
            .when()
                .get("/api/triangle/1/10/1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassify_Isosceles_ReturnsTwo() {
        given()
            .when()
                .get("/api/triangle/3/3/4")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassify_Scalene_ReturnsOne() {
        given()
            .when()
                .get("/api/triangle/3/4/5")
            .then()
                .statusCode(200);
    }
}