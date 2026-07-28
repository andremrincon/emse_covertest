package ts01qwen3_7_plus;

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
    public void testBessjValidSmallN() {
        given()
            .when()
            .get("/api/bessj/3/2.5")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjValidLargeN() {
        given()
            .when()
            .get("/api/bessj/1000/1e-10")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjInvalidNLow() {
        given()
            .when()
            .get("/api/bessj/2/1.0")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjInvalidNHigh() {
        given()
            .when()
            .get("/api/bessj/1001/1.0")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherValidModerate() {
        given()
            .when()
            .get("/api/fisher/10/5/0.75")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherValidSmallX() {
        given()
            .when()
            .get("/api/fisher/1/1/0.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidM() {
        given()
            .when()
            .get("/api/fisher/1001/5/0.5")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidN() {
        given()
            .when()
            .get("/api/fisher/10/1001/0.5")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqValidModerate() {
        given()
            .when()
            .get("/api/gammq/5.5/2.3")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqValidSmallALargeX() {
        given()
            .when()
            .get("/api/gammq/0.001/1000.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderValidPositive() {
        given()
            .when()
            .get("/api/remainder/17/5")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderValidNegative() {
        given()
            .when()
            .get("/api/remainder/-9/4")
            .then()
            .statusCode(200);
    }
}