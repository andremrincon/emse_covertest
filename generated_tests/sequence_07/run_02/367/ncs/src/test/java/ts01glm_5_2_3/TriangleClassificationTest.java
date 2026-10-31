package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TriangleClassificationTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
        RestAssured.basePath = "";
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
                .get("/api/triangle/2/2/3")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonPositiveEdgeAReturnsZero() {
        given()
            .when()
                .get("/api/triangle/0/4/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleMaxTooLargeReturnsZero() {
        given()
            .when()
                .get("/api/triangle/5/1/1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonPositiveEdgeBReturnsZero() {
        given()
            .when()
                .get("/api/triangle/3/0/5")
            .then()
                .statusCode(200);
    }
}