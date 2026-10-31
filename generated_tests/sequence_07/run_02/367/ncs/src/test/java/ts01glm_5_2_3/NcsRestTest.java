package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void fisher_mExceeds1000_returns400() {
        given()
                .when()
                .get("/api/fisher/1001/5/0.75")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void fisher_nExceeds1000_returns400() {
        given()
                .when()
                .get("/api/fisher/10/1001/0.75")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void fisher_validParams_returns200() {
        given()
                .when()
                .get("/api/fisher/10/5/0.75")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void remainder_aExceedsLimit_returns400() {
        given()
                .when()
                .get("/api/remainder/10001/5")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void remainder_bExceedsNegativeLimit_returns400() {
        given()
                .when()
                .get("/api/remainder/17/-10001")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void remainder_validParams_returns200() {
        given()
                .when()
                .get("/api/remainder/17/5")
                .then()
                .statusCode(200);
    }
}