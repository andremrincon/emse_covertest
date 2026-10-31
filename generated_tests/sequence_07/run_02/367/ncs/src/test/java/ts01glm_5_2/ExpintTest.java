package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ExpintTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testExpintErrorWhenNIsNegative() {
        given()
        .when()
            .get("/api/expint/-1/2.5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintWhenNIsZero() {
        given()
        .when()
            .get("/api/expint/0/2.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintWhenXIsZeroAndNGreaterThanOne() {
        given()
        .when()
            .get("/api/expint/3/0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintContinuedFractionWhenXGreaterThanOne() {
        given()
        .when()
            .get("/api/expint/3/2.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesWhenNGreaterThanOne() {
        given()
        .when()
            .get("/api/expint/3/0.1")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesWhenNEqualsOne() {
        given()
        .when()
            .get("/api/expint/1/0.1")
        .then()
            .statusCode(200);
    }
}