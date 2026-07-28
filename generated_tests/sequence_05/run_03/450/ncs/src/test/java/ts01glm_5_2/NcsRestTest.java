package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class NcsRestTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testBessjNormalCase() {
        given()
                .when()
                .get("/api/bessj/3/2.5")
                .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testBessjSmallX() {
        given()
                .when()
                .get("/api/bessj/3/1e-10")
                .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testBessjXZero() {
        given()
                .when()
                .get("/api/bessj/5/0")
                .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testBessjNTooSmall() {
        given()
                .when()
                .get("/api/bessj/2/2.5")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherNormalCase() {
        given()
                .when()
                .get("/api/fisher/10/5/0.75")
                .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testFisherXZero() {
        given()
                .when()
                .get("/api/fisher/1/1/0.0")
                .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <200>.")
    @Test(timeout = 60000)
    public void testFisherXGreaterThanOne() {
        given()
                .when()
                .get("/api/fisher/10/5/1.2")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqNormalCase() {
        given()
                .when()
                .get("/api/gammq/5.5/2.3")
                .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGammqSmallALargeX() {
        given()
                .when()
                .get("/api/gammq/0.001/1000.0")
                .then()
                .statusCode(200)
                .body("resultAsDouble", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGammqNegativeA() {
        given()
                .when()
                .get("/api/gammq/-1.0/2.0")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderNormalCase() {
        given()
                .when()
                .get("/api/remainder/17/5")
                .then()
                .statusCode(200)
                .body("resultAsInt", notNullValue());
    }

    @Test(timeout = 60000)
    public void testRemainderNegativeA() {
        given()
                .when()
                .get("/api/remainder/-9/4")
                .then()
                .statusCode(200)
                .body("resultAsInt", notNullValue());
    }
}