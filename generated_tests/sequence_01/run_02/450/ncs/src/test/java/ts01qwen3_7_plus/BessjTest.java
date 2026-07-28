package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testBessj_nLessThan2() {
        given()
            .baseUri(baseUrl)
            .pathParam("n", 1)
            .pathParam("x", 2.5)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_xZero() {
        given()
            .baseUri(baseUrl)
            .pathParam("n", 3)
            .pathParam("x", 0.0)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_axGtN_axLt8() {
        given()
            .baseUri(baseUrl)
            .pathParam("n", 2)
            .pathParam("x", 3.0)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_axGtN_axGte8_xPos() {
        given()
            .baseUri(baseUrl)
            .pathParam("n", 2)
            .pathParam("x", 10.0)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_axGtN_axGte8_xNeg() {
        given()
            .baseUri(baseUrl)
            .pathParam("n", 2)
            .pathParam("x", -10.0)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_axLteN_xPos() {
        given()
            .baseUri(baseUrl)
            .pathParam("n", 5)
            .pathParam("x", 3.0)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_axLteN_xNeg_nOdd() {
        given()
            .baseUri(baseUrl)
            .pathParam("n", 3)
            .pathParam("x", -2.0)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_axLteN_xNeg_nEven() {
        given()
            .baseUri(baseUrl)
            .pathParam("n", 4)
            .pathParam("x", -2.0)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_invalidN() {
        given()
            .baseUri(baseUrl)
            .pathParam("n", "abc")
            .pathParam("x", 2.5)
        .when()
            .get("/api/bessj/{n}/{x}")
        .then()
            .statusCode(400);
    }
}