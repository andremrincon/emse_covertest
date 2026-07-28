package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", System.getenv().getOrDefault("BASE_URL", "http://localhost:8080"));
    }

    @Test(timeout = 60000)
    public void testFisherNormalCase() {
        given()
            .when()
                .get("/api/fisher/10/5/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherWithXZero() {
        given()
            .when()
                .get("/api/fisher/1/1/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherWithNegativeN() {
        given()
            .when()
                .get("/api/fisher/10/-3/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNormalCase() {
        given()
            .when()
                .get("/api/remainder/17/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderWithNegativeDividend() {
        given()
            .when()
                .get("/api/remainder/-9/4")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderWithOutOfLimitsValue() {
        given()
            .when()
                .get("/api/remainder/10001/5")
            .then()
                .statusCode(400);
    }
}