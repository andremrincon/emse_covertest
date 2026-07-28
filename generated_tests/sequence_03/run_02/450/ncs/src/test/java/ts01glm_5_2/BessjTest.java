package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BessjTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
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
    public void bessj_xZero_returns200() {
        given()
                .pathParam("n", 3)
                .pathParam("x", 0.0)
                .when()
                .get("/api/bessj/{n}/{x}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_axGreaterThanNAndAxGe8_returns200() {
        given()
                .pathParam("n", 3)
                .pathParam("x", 10.0)
                .when()
                .get("/api/bessj/{n}/{x}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_axGreaterThanNAndAxLt8_returns200() {
        given()
                .pathParam("n", 3)
                .pathParam("x", 2.5)
                .when()
                .get("/api/bessj/{n}/{x}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_axLessThanOrEqualN_returns200() {
        given()
                .pathParam("n", 10)
                .pathParam("x", 2.5)
                .when()
                .get("/api/bessj/{n}/{x}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_negativeXOddN_returns200() {
        given()
                .pathParam("n", 3)
                .pathParam("x", -2.5)
                .when()
                .get("/api/bessj/{n}/{x}")
                .then()
                .statusCode(200);
    }
}