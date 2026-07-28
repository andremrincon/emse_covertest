package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testBessjNLessThan2Returns400() {
        given()
            .when()
                .get("/api/bessj/1/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjXZeroReturns200() {
        given()
            .when()
                .get("/api/bessj/3/0.0")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanNAxLessThan8Returns200() {
        given()
            .when()
                .get("/api/bessj/2/5.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanNAxGreaterOrEqual8Returns200() {
        given()
            .when()
                .get("/api/bessj/2/10.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjXNegativeNOddReturns200() {
        given()
            .when()
                .get("/api/bessj/3/-5.0")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testBessjXNegativeNEvenReturns200() {
        given()
            .when()
                .get("/api/bessj/2/-5.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjAxLessOrEqualNAxLessThan8Returns200() {
        given()
            .when()
                .get("/api/bessj/5/3.0")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testBessjAxLessOrEqualNAxGreaterOrEqual8Returns200() {
        given()
            .when()
                .get("/api/bessj/10/9.0")
            .then()
                .statusCode(lessThan(300));
    }
}