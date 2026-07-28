package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testBessjNormalCase() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/3/2.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjSmallX() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/3/1e-10")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNTooSmall() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/2/2.5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjNTooLarge() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/1001/2.5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherNormalCase() {
        given()
            .accept("application/json")
        .when()
            .get("/api/fisher/10/5/0.75")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherXZero() {
        given()
            .accept("application/json")
        .when()
            .get("/api/fisher/1/1/0.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherMTooLarge() {
        given()
            .accept("application/json")
        .when()
            .get("/api/fisher/1001/5/0.75")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqXLessThanA() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/5.5/1.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqXGreaterThanA() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/0.001/1000.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidA() {
        given()
            .accept("application/json")
        .when()
            .get("/api/gammq/-1.0/2.0")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderNormalCase() {
        given()
            .accept("application/json")
        .when()
            .get("/api/remainder/17/5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeA() {
        given()
            .accept("application/json")
        .when()
            .get("/api/remainder/-9/4")
        .then()
            .statusCode(200);
    }
}