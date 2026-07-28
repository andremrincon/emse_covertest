package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class NcsRestTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testBessjXZero() {
        given()
            .when()
                .get("/api/bessj/3/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjVerySmallX() {
        given()
            .when()
                .get("/api/bessj/3/0.0000000001")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjLargeN() {
        given()
            .when()
                .get("/api/bessj/100/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherNormalValues() {
        given()
            .when()
                .get("/api/fisher/10/5/0.75")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherXZero() {
        given()
            .when()
                .get("/api/fisher/1/1/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherSmallMN() {
        given()
            .when()
                .get("/api/fisher/2/3/0.25")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderPositiveValues() {
        given()
            .when()
                .get("/api/remainder/17/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeDividend() {
        given()
            .when()
                .get("/api/remainder/-9/4")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeDivisor() {
        given()
            .when()
                .get("/api/remainder/17/-5")
            .then()
                .statusCode(200);
    }
}