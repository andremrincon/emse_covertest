package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NcsRestTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testBessjValidN() {
        given()
                .when()
                .get("/api/bessj/3/2.5")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNLessThanOrEqual2() {
        given()
                .when()
                .get("/api/bessj/0/2.5")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjNGreaterThan1000() {
        given()
                .when()
                .get("/api/bessj/1001/2.5")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherMGreaterThan1000() {
        given()
                .when()
                .get("/api/fisher/1001/5/0.75")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherValidParams() {
        given()
                .when()
                .get("/api/fisher/10/5/0.75")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherNGreaterThan1000() {
        given()
                .when()
                .get("/api/fisher/10/1001/0.75")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqValidParams() {
        given()
                .when()
                .get("/api/gammq/5.5/2.3")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidParams() {
        given()
                .when()
                .get("/api/gammq/-1.0/2.0")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderValidParams() {
        given()
                .when()
                .get("/api/remainder/17/5")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderOutOfRange() {
        given()
                .when()
                .get("/api/remainder/10001/5")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintValidParams() {
        given()
                .when()
                .get("/api/expint/3/2.5")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleValidParams() {
        given()
                .when()
                .get("/api/triangle/3/4/5")
                .then()
                .statusCode(200);
    }
}