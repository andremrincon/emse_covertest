package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class ExpintTest {

    @Test(timeout = 60000)
    public void testExpintInvalidParameters() {
        given()
            .pathParam("n", -1)
            .pathParam("x", 2.5)
        .when()
            .get("http://localhost:8080/api/expint/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintNZero() {
        given()
            .pathParam("n", 0)
            .pathParam("x", 2.5)
        .when()
            .get("http://localhost:8080/api/expint/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXZero() {
        given()
            .pathParam("n", 3)
            .pathParam("x", 0.0)
        .when()
            .get("http://localhost:8080/api/expint/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXGreaterThanOne() {
        given()
            .pathParam("n", 3)
            .pathParam("x", 2.5)
        .when()
            .get("http://localhost:8080/api/expint/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXLessThanOrEqualToOne() {
        given()
            .pathParam("n", 3)
            .pathParam("x", 0.5)
        .when()
            .get("http://localhost:8080/api/expint/{n}/{x}")
        .then()
            .statusCode(200);
    }
}