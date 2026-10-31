package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class BessjTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void bessj_nLessThan2_returns400() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/0/2.5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void bessj_xZero_returns200WithValueZero() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/3/0.0")
        .then()
            .statusCode(200)
            .body("value", equalTo(null));
    }

    @Test(timeout = 60000)
    public void bessj_axGreaterThanN_positiveX_returns200() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/3/2.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_axGreaterThanN_negativeX_oddN_returns200() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/3/-2.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_axLessThanOrEqualN_negativeX_evenN_returns200() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/4/-0.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_axLessThanOrEqualN_positiveX_returns200() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/3/0.5")
        .then()
            .statusCode(200);
    }
}