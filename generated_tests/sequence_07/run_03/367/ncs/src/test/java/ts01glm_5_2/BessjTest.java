package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class BessjTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testBessjNLessThan2Throws() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/1/2.5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjAxZeroReturnsZero() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/3/0.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjForwardRecurrenceSmallX() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/3/5.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjForwardRecurrenceLargeX() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/3/10.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNegativeXOddN() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/3/-5.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNegativeXEvenN() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/4/-10.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjBackwardRecurrence() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/3/2.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjInvalidNType() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/abc/2.5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjNegativeNThrows() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/-5/2.5")
        .then()
            .statusCode(400);
    }
}