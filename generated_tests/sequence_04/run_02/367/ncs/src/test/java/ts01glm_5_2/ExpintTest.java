package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ExpintTest {

    @Before
    public void setUp() {
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
    public void testExpintN0Path() {
        given()
            .accept("application/json")
        .when()
            .get("/api/expint/0/1.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintNegativeNError() {
        given()
            .accept("application/json")
        .when()
            .get("/api/expint/-1/1.0")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintContinuedFractionConvergence() {
        given()
            .accept("application/json")
        .when()
            .get("/api/expint/3/2.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXZeroNGreaterThanOne() {
        given()
            .accept("application/json")
        .when()
            .get("/api/expint/2/0.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesNm1Zero() {
        given()
            .accept("application/json")
        .when()
            .get("/api/expint/1/0.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesNm1NonZero() {
        given()
            .accept("application/json")
        .when()
            .get("/api/expint/2/0.5")
        .then()
            .statusCode(200);
    }
}