package ts01glm_5_2_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;

public class GammqTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void gammq_gser_normalPath_returns200() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/5.5/2.3")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void gammq_gcf_normalPath_returns200() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/5.5/1000.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void gammq_gser_xZero_returns200() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/5.5/0.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void gammq_invalidA_negative_returns400() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/-1.0/2.3")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void gammq_invalidA_nonNumeric_returns400() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/abc/2.3")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void gammq_gcf_smallA_largeX_returns200() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/0.001/1000.0")
        .then()
            .statusCode(200);
    }
}