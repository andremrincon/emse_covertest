package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testFisherMExceedsLimitReturns400() {
        given()
            .when()
                .get("/api/fisher/1001/5/0.75")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherNExceedsLimitReturns400() {
        given()
            .when()
                .get("/api/fisher/10/1001/0.75")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherValidParamsReturns200() {
        given()
            .when()
                .get("/api/fisher/10/5/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderAExceedsPositiveLimitReturns400() {
        given()
            .when()
                .get("/api/remainder/10001/5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderAExceedsNegativeLimitReturns400() {
        given()
            .when()
                .get("/api/remainder/-10001/5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderValidParamsReturns200() {
        given()
            .when()
                .get("/api/remainder/17/5")
            .then()
                .statusCode(200);
    }
}