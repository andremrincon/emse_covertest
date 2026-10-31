package ts01qwen3_7_plus_3;

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
    public void testBessjWithNLessThan2ThrowsException() {
        given()
            .when()
                .get("/api/bessj/1/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjWithZeroNThrowsException() {
        given()
            .when()
                .get("/api/bessj/0/5.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjWithNegativeNThrowsException() {
        given()
            .when()
                .get("/api/bessj/-5/3.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjWithAxGreaterThanNAndLargeX() {
        given()
            .when()
                .get("/api/bessj/3/10.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjWithAxGreaterThanNAndVeryLargeX() {
        given()
            .when()
                .get("/api/bessj/2/15.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjWithAxLessThanOrEqualToN() {
        given()
            .when()
                .get("/api/bessj/5/2.5")
            .then()
                .statusCode(200);
    }
}