package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ExpintTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testExpintErrorNLessThanZero() {
        given()
            .when()
                .get("/api/expint/-1/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintNZeroXPositive() {
        given()
            .when()
                .get("/api/expint/0/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintNGreaterThanOneXZero() {
        given()
            .when()
                .get("/api/expint/3/0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintContinuedFractionXGreaterThanOne() {
        given()
            .when()
                .get("/api/expint/3/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesNOneXLessThanOne() {
        given()
            .when()
                .get("/api/expint/1/0.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesNGreaterThanOneXLessThanOne() {
        given()
            .when()
                .get("/api/expint/3/0.1")
            .then()
                .statusCode(200);
    }
}