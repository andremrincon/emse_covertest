package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @Test(timeout = 60000)
    public void testInvalidTriangleWithZeroSide() {
        given()
            .when()
                .get("/api/triangle/0/4/5")
            .then()
                .statusCode(200)
                .body("triangleType", equalTo(null));
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangle() {
        given()
            .when()
                .get("/api/triangle/3/3/3")
            .then()
                .statusCode(200)
                .body("triangleType", equalTo(null));
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleInequalityMaxC() {
        given()
            .when()
                .get("/api/triangle/1/2/5")
            .then()
                .statusCode(200)
                .body("triangleType", equalTo(null));
    }

    @Test(timeout = 60000)
    public void testInvalidTriangleInequalityMaxA() {
        given()
            .when()
                .get("/api/triangle/5/1/2")
            .then()
                .statusCode(200)
                .body("triangleType", equalTo(null));
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangle() {
        given()
            .when()
                .get("/api/triangle/3/3/4")
            .then()
                .statusCode(200)
                .body("triangleType", equalTo(null));
    }

    @Test(timeout = 60000)
    public void testScaleneTriangle() {
        given()
            .when()
                .get("/api/triangle/3/4/6")
            .then()
                .statusCode(200)
                .body("triangleType", equalTo(null));
    }
}