package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
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
    public void bessj_xZero_returns200WithValueZero() {
        given()
                .pathParam("n", 3)
                .pathParam("x", 0)
                .when()
                .get("/api/bessj/{n}/{x}")
                .then()
                .statusCode(200)
                .body("resultAsDouble", equalTo(0.0));
    }

    @Test(timeout = 60000)
    public void bessj_axGreaterThanN_positiveX_smallAx_returns200() {
        given()
                .pathParam("n", 3)
                .pathParam("x", 2.5)
                .when()
                .get("/api/bessj/{n}/{x}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_axGreaterThanN_positiveX_largeAx_returns200() {
        given()
                .pathParam("n", 3)
                .pathParam("x", 10.0)
                .when()
                .get("/api/bessj/{n}/{x}")
                .then()
                .statusCode(200);
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
    public void bessj_axLessThanOrEqualN_returns200() {
        given()
                .pathParam("n", 5)
                .pathParam("x", 2.5)
                .when()
                .get("/api/bessj/{n}/{x}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_negativeX_largeAx_oddN_returns200() {
        given()
                .pathParam("n", 3)
                .pathParam("x", -10.0)
                .when()
                .get("/api/bessj/{n}/{x}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_axLessThanOrEqualN_negativeX_returns200() {
        given()
                .pathParam("n", 5)
                .pathParam("x", -2.5)
                .when()
                .get("/api/bessj/{n}/{x}")
                .then()
                .statusCode(200);
    }
}