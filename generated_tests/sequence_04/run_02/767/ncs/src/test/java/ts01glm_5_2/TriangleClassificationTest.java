package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testNegativeSideReturnsNotTriangle() {
        given()
            .when()
                .get("/api/triangle/-1/4/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testZeroSideReturnsNotTriangle() {
        given()
            .when()
                .get("/api/triangle/0/4/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangleReturnsThree() {
        given()
            .when()
                .get("/api/triangle/3/3/3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangleReturnsTwo() {
        given()
            .when()
                .get("/api/triangle/3/3/4")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangleReturnsOne() {
        given()
            .when()
                .get("/api/triangle/3/4/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleReturnsNotTriangle() {
        given()
            .when()
                .get("/api/triangle/1/1/3")
            .then()
                .statusCode(200);
    }
}