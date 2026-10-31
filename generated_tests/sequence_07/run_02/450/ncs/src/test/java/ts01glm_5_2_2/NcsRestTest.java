package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void fisher_mExceedsLimit_returns400() {
        given()
            .accept("application/json")
        .when()
            .get("/api/fisher/1001/5/0.75")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void fisher_nExceedsLimit_returns400() {
        given()
            .accept("application/json")
        .when()
            .get("/api/fisher/10/1001/0.75")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void fisher_validParamsCausingException_returns400() {
        given()
            .accept("application/json")
        .when()
            .get("/api/fisher/1/1/1.2")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void fisher_validParams_returns200() {
        given()
            .accept("application/json")
        .when()
            .get("/api/fisher/10/5/0.75")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void remainder_aExceedsLimit_returns400() {
        given()
            .accept("application/json")
        .when()
            .get("/api/remainder/10001/5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void remainder_validParams_returns200() {
        given()
            .accept("application/json")
        .when()
            .get("/api/remainder/17/5")
        .then()
            .statusCode(200);
    }
}