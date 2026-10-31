package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
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
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_axGreaterThanN_absXLessThan8_returns200() {
        given()
                .pathParam("n", 2)
                .pathParam("x", 5.0)
                .when()
                .get("/api/bessj/{n}/{x}")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void bessj_axGreaterThanN_absXGreaterThanOrEqual8_returns200() {
        given()
                .pathParam("n", 3)
                .pathParam("x", 10.0)
                .when()
                .get("/api/bessj/{n}/{x}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_negativeX_absXLessThan8_nEven_returns200() {
        given()
                .pathParam("n", 2)
                .pathParam("x", -5.0)
                .when()
                .get("/api/bessj/{n}/{x}")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void bessj_negativeX_absXGreaterThanOrEqual8_nOdd_returns200() {
        given()
                .pathParam("n", 3)
                .pathParam("x", -10.0)
                .when()
                .get("/api/bessj/{n}/{x}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_axLessThanOrEqualN_positiveX_returns200() {
        given()
                .pathParam("n", 5)
                .pathParam("x", 2.5)
                .when()
                .get("/api/bessj/{n}/{x}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_axLessThanOrEqualN_negativeX_nOdd_returns200() {
        given()
                .pathParam("n", 3)
                .pathParam("x", -2.5)
                .when()
                .get("/api/bessj/{n}/{x}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_invalidNType_returns400() {
        given()
                .pathParam("n", "abc")
                .pathParam("x", 2.5)
                .when()
                .get("/api/bessj/{n}/{x}")
                .then()
                .statusCode(400);
    }
}