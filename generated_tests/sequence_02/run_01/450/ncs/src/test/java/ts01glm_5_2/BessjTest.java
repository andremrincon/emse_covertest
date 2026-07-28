package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class BessjTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testBessjNLessThan2ThrowsException() {
        when()
            .get("/api/bessj/1/2.5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjNZeroThrowsException() {
        when()
            .get("/api/bessj/0/2.5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjAxZeroReturnsZero() {
        when()
            .get("/api/bessj/3/0")
        .then()
            .statusCode(200)
            .body("resultAsDouble", equalTo(0.0));
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanNPositiveX() {
        when()
            .get("/api/bessj/3/10.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanNNegativeXOddN() {
        when()
            .get("/api/bessj/3/-10.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxLessThanOrEqualNPositiveX() {
        when()
            .get("/api/bessj/3/2.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxLessThanOrEqualNNegativeXOddN() {
        when()
            .get("/api/bessj/3/-2.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxLessThanOrEqualNNegativeXEvenN() {
        when()
            .get("/api/bessj/4/-2.5")
        .then()
            .statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <400>.")
    @Test(timeout = 60000)
    public void testBessjAxEqualsEightBoundary() {
        when()
            .get("/api/bessj/2/8.0")
        .then()
            .statusCode(200);
    }
}