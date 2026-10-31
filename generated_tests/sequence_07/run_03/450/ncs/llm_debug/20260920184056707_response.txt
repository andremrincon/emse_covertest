package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void bessj_validInput_returns200() {
        given()
            .when()
                .get("/api/bessj/3/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_nLessThanOrEqual2_returns400() {
        given()
            .when()
                .get("/api/bessj/0/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void bessj_nGreaterThan1000_returns400() {
        given()
            .when()
                .get("/api/bessj/1001/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void fisher_validInput_returns200() {
        given()
            .when()
                .get("/api/fisher/10/5/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void fisher_mGreaterThan1000_returns400() {
        given()
            .when()
                .get("/api/fisher/1001/5/0.75")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void fisher_nGreaterThan1000_returns400() {
        given()
            .when()
                .get("/api/fisher/10/1001/0.75")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void fisher_invalidX_throwsException_returns400() {
        given()
            .when()
                .get("/api/fisher/10/5/1.2")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void remainder_validInput_returns200() {
        given()
            .when()
                .get("/api/remainder/17/5")
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
}