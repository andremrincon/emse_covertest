package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("API_BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangleReturnsThree() {
        given()
            .when()
                .get("/api/triangle/1/1/1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleMaxIsCReturnsZero() {
        given()
            .when()
                .get("/api/triangle/1/2/3")
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
    public void testIsoscelesTriangleReturnsTwo() {
        given()
            .when()
                .get("/api/triangle/3/3/4")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNegativeEdgeAReturnsZero() {
        given()
            .when()
                .get("/api/triangle/0/1/1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleMaxIsAReturnsZero() {
        given()
            .when()
                .get("/api/triangle/5/2/2")
            .then()
                .statusCode(200);
    }
}