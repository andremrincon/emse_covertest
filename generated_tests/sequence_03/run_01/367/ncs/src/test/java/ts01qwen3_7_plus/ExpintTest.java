package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ExpintTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("test.base.uri", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testExpintNegativeN() {
        given()
            .when()
                .get("/api/expint/-1/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintNegativeX() {
        given()
            .when()
                .get("/api/expint/1/-999.9")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintZeroXZeroN() {
        given()
            .when()
                .get("/api/expint/0/0.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintContinuedFraction() {
        given()
            .when()
                .get("/api/expint/3/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesNm1NotZero() {
        given()
            .when()
                .get("/api/expint/3/0.1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesNm1Zero() {
        given()
            .when()
                .get("/api/expint/1/0.1")
            .then()
                .statusCode(200);
    }
}