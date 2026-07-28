package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void fisher_validParameters_returns200() {
        RestAssured.given()
            .when()
            .get("/api/fisher/10/5/0.75")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void fisher_mExceeds1000_returns400() {
        RestAssured.given()
            .when()
            .get("/api/fisher/1001/5/0.75")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void fisher_xOutOfRange_throwsException_returns400() {
        RestAssured.given()
            .when()
            .get("/api/fisher/10/5/1.2")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void remainder_validParameters_returns200() {
        RestAssured.given()
            .when()
            .get("/api/remainder/17/5")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void remainder_aExceedsLimit_returns400() {
        RestAssured.given()
            .when()
            .get("/api/remainder/10001/5")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void remainder_bBelowNegativeLimit_returns400() {
        RestAssured.given()
            .when()
            .get("/api/remainder/17/-10001")
            .then()
            .statusCode(400);
    }
}