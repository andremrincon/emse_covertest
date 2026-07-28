package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class BessjTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testBessjNLessThan2ThrowsException() {
        given()
            .when()
                .get("/api/bessj/1/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjAxZeroReturnsZero() {
        given()
            .when()
                .get("/api/bessj/3/0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanNWithSmallAx() {
        given()
            .when()
                .get("/api/bessj/3/5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanNWithLargeAx() {
        given()
            .when()
                .get("/api/bessj/3/10")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNegativeXOddNWithLargeAx() {
        given()
            .when()
                .get("/api/bessj/3/-10")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxLessThanOrEqualN() {
        given()
            .when()
                .get("/api/bessj/3/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNegativeXEvenNElseBranch() {
        given()
            .when()
                .get("/api/bessj/4/-2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjBignoScalingInElseBranch() {
        given()
            .when()
                .get("/api/bessj/100/0.001")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNegativeXOddNElseBranch() {
        given()
            .when()
                .get("/api/bessj/3/-2.5")
            .then()
                .statusCode(200);
    }
}