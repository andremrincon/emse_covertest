package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testBessjNLessThan2Returns400() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/1/2.5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjXZeroReturns200() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/3/0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanNSmallAx() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/3/5.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanNLargeAxPositive() {
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
            .get("/api/bessj/3/-10.0")
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
    public void testBessjAxLessThanOrEqualN() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/3/2.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNegativeXSmallAxOddN() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/3/-5.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNegativeXSmallAxEvenN() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/4/-5.0")
        .then()
            .statusCode(200);
    }
}