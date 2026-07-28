package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertNotNull;

public class BessjTest {

    @BeforeClass
    public static void setUp() {
        String host = System.getenv().getOrDefault("API_HOST", "localhost");
        String port = System.getenv().getOrDefault("API_PORT", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
    }

    @Test(timeout = 60000)
    public void bessj_nLessThan2_returns400() {
        given()
                .pathParam("n", 1)
                .pathParam("x", 2.5)
        .when()
                .get("/api/bessj/{n}/{x}")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void bessj_nZero_returns400() {
        given()
                .pathParam("n", 0)
                .pathParam("x", 2.5)
        .when()
                .get("/api/bessj/{n}/{x}")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void bessj_negativeX_oddN_returns200() {
        given()
                .pathParam("n", 3)
                .pathParam("x", -2.5)
        .when()
                .get("/api/bessj/{n}/{x}")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_negativeX_evenN_returns200() {
        given()
                .pathParam("n", 4)
                .pathParam("x", -2.5)
        .when()
                .get("/api/bessj/{n}/{x}")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_negativeX_largeAbsoluteValue_returns200() {
        given()
                .pathParam("n", 3)
                .pathParam("x", -10.0)
        .when()
                .get("/api/bessj/{n}/{x}")
        .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_xZero_returns200() {
        given()
                .pathParam("n", 3)
                .pathParam("x", 0.0)
        .when()
                .get("/api/bessj/{n}/{x}")
        .then()
                .statusCode(200);
    }
}