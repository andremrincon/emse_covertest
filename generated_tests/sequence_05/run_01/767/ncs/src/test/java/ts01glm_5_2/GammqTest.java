package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GammqTest {

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
    public void gammq_gserSeriesPath_returns200() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/5.5/2.3")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void gammq_gcfContinuedFractionPath_returns200() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/0.001/1000.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void gammq_exeNegativeX_returns400() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/5.5/-1.0")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void gammq_exeNonPositiveA_returns400() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/0.0/2.3")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void gammq_gserXZero_returns200() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/5.5/0.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void gammq_invalidAType_returns400() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/abc/2.3")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void gammq_gcfBoundaryXEqualsAPlusOne_returns200() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/1.0/2.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void gammq_gserSmallAAndSmallX_returns200() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/0.001/0.0005")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void gammq_invalidXType_returns400() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/5.5/abc")
        .then()
            .statusCode(400);
    }
}