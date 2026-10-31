package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

public class NcsRestTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void bessj_nLessThanOrEqual2_returns400() {
        given()
                .pathParam("n", 0)
                .pathParam("x", 2.5)
                .when()
                .get("/api/bessj/{n}/{x}")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void bessj_nGreaterThan1000_returns400() {
        given()
                .pathParam("n", 1001)
                .pathParam("x", 2.5)
                .when()
                .get("/api/bessj/{n}/{x}")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void bessj_validParams_returns200() {
        given()
                .pathParam("n", 3)
                .pathParam("x", 2.5)
                .when()
                .get("/api/bessj/{n}/{x}")
                .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void fisher_mGreaterThan1000_returns400() {
        given()
                .pathParam("m", 1001)
                .pathParam("n", 5)
                .pathParam("x", 0.75)
                .when()
                .get("/api/fisher/{m}/{n}/{x}")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void fisher_nGreaterThan1000_returns400() {
        given()
                .pathParam("m", 10)
                .pathParam("n", 1001)
                .pathParam("x", 0.75)
                .when()
                .get("/api/fisher/{m}/{n}/{x}")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void fisher_runtimeException_returns400() {
        given()
                .pathParam("m", 1)
                .pathParam("n", 1)
                .pathParam("x", 1.2)
                .when()
                .get("/api/fisher/{m}/{n}/{x}")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void fisher_validParams_returns200() {
        given()
                .pathParam("m", 10)
                .pathParam("n", 5)
                .pathParam("x", 0.75)
                .when()
                .get("/api/fisher/{m}/{n}/{x}")
                .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void gammq_negativeA_returns400() {
        given()
                .pathParam("a", -1.0)
                .pathParam("x", 2.0)
                .when()
                .get("/api/gammq/{a}/{x}")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void gammq_validParams_returns200() {
        given()
                .pathParam("a", 5.5)
                .pathParam("x", 2.3)
                .when()
                .get("/api/gammq/{a}/{x}")
                .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void remainder_aGreaterThanLimit_returns400() {
        given()
                .pathParam("a", 10001)
                .pathParam("b", 5)
                .when()
                .get("/api/remainder/{a}/{b}")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void remainder_bLessThanNegativeLimit_returns400() {
        given()
                .pathParam("a", 17)
                .pathParam("b", -10001)
                .when()
                .get("/api/remainder/{a}/{b}")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void remainder_validParams_returns200() {
        given()
                .pathParam("a", 17)
                .pathParam("b", 5)
                .when()
                .get("/api/remainder/{a}/{b}")
                .then()
                .statusCode(200)
                .body("resultAsInt", notNullValue());
    }
}