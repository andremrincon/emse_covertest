package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    private String getBaseUrl() {
        String envUrl = System.getenv("BASE_URL");
        return (envUrl != null && !envUrl.isEmpty()) ? envUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testExpintXGreaterThan1() {
        given()
            .basePath(getBaseUrl())
            .pathParam("n", 3)
            .pathParam("x", 2.5)
        .when()
            .get("/api/expint/{n}/{x}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testExpintXLessThanOrEqualTo1() {
        given()
            .basePath(getBaseUrl())
            .pathParam("n", 1)
            .pathParam("x", 0.5)
        .when()
            .get("/api/expint/{n}/{x}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testExpintNegativeN() {
        given()
            .basePath(getBaseUrl())
            .pathParam("n", -1)
            .pathParam("x", 2.5)
        .when()
            .get("/api/expint/{n}/{x}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testExpintNegativeX() {
        given()
            .basePath(getBaseUrl())
            .pathParam("n", 3)
            .pathParam("x", -2.5)
        .when()
            .get("/api/expint/{n}/{x}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testExpintZeroXAndZeroN() {
        given()
            .basePath(getBaseUrl())
            .pathParam("n", 0)
            .pathParam("x", 0.0)
        .when()
            .get("/api/expint/{n}/{x}")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testExpintZeroXAndOneN() {
        given()
            .basePath(getBaseUrl())
            .pathParam("n", 1)
            .pathParam("x", 0.0)
        .when()
            .get("/api/expint/{n}/{x}")
        .then()
            .statusCode(404);
    }
}