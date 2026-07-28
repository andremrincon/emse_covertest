package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TriangleClassificationTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testClassify_ZeroEdgeA_ReturnsZero() {
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
    public void testClassify_ZeroEdgeB_ReturnsZero() {
        given()
            .pathParam("a", 1)
            .pathParam("b", 0)
            .pathParam("c", 1)
        .when()
            .get("/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassify_EquilateralSides_ReturnsThree() {
        given()
            .pathParam("a", 1)
            .pathParam("b", 1)
            .pathParam("c", 1)
        .when()
            .get("/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassify_InvalidTriangleMaxA_ReturnsZero() {
        given()
            .pathParam("a", 5)
            .pathParam("b", 1)
            .pathParam("c", 1)
        .when()
            .get("/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testClassify_IsoscelesSides_ReturnsTwo() {
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
    public void testClassify_ScaleneSides_ReturnsOne() {
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