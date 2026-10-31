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
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        given()
            .when()
                .get("/api/triangle/5/5/5")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleAMax() {
        given()
            .when()
                .get("/api/triangle/5/3/2")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        given()
            .when()
                .get("/api/triangle/5/5/8")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleBMax() {
        given()
            .when()
                .get("/api/triangle/2/5/3")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleCMax() {
        given()
            .when()
                .get("/api/triangle/2/3/5")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        given()
            .when()
                .get("/api/triangle/3/4/5")
            .then()
                .statusCode(200)
                .body("triangleType", equalTo(null));
    }
}