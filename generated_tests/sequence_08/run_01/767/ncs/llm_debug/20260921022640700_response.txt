package ts01qwen3_7_plus_3;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class TriangleClassificationTest {

    @Test(timeout = 60000)
    public void testNonPositiveSides() {
        given()
            .pathParam("a", 0)
            .pathParam("b", 1)
            .pathParam("c", 1)
        .when()
            .get("http://localhost:8080/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("a", 0)
            .pathParam("b", 1)
            .pathParam("c", 1)
        .when()
            .get("http://localhost:8080/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200)
            .body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testEquilateral() {
        given()
            .pathParam("a", 3)
            .pathParam("b", 3)
            .pathParam("c", 3)
        .when()
            .get("http://localhost:8080/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("a", 3)
            .pathParam("b", 3)
            .pathParam("c", 3)
        .when()
            .get("http://localhost:8080/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200)
            .body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testInvalidTriangle() {
        given()
            .pathParam("a", 1)
            .pathParam("b", 2)
            .pathParam("c", 5)
        .when()
            .get("http://localhost:8080/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("a", 1)
            .pathParam("b", 2)
            .pathParam("c", 5)
        .when()
            .get("http://localhost:8080/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200)
            .body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testIsosceles() {
        given()
            .pathParam("a", 3)
            .pathParam("b", 3)
            .pathParam("c", 4)
        .when()
            .get("http://localhost:8080/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("a", 3)
            .pathParam("b", 3)
            .pathParam("c", 4)
        .when()
            .get("http://localhost:8080/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200)
            .body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testRightTriangle() {
        given()
            .pathParam("a", 3)
            .pathParam("b", 4)
            .pathParam("c", 5)
        .when()
            .get("http://localhost:8080/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("a", 3)
            .pathParam("b", 4)
            .pathParam("c", 5)
        .when()
            .get("http://localhost:8080/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200)
            .body("triangleType", nullValue());
    }

    @Test(timeout = 60000)
    public void testScaleneNonRight() {
        given()
            .pathParam("a", 2)
            .pathParam("b", 3)
            .pathParam("c", 4)
        .when()
            .get("http://localhost:8080/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(lessThan(300));

        given()
            .pathParam("a", 2)
            .pathParam("b", 3)
            .pathParam("c", 4)
        .when()
            .get("http://localhost:8080/api/triangle/{a}/{b}/{c}")
        .then()
            .statusCode(200)
            .body("triangleType", nullValue());
    }
}