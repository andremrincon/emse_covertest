package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class BessjTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            RestAssured.baseURI = "http://localhost:8080";
        } else {
            RestAssured.baseURI = baseUrl;
        }
    }

    @Test(timeout = 60000)
    public void testBessjNLessThan2() {
        given()
            .when()
                .get("/api/bessj/1/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjXZero() {
        given()
            .when()
                .get("/api/bessj/3/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjForwardRecurrenceSmallX() {
        given()
            .when()
                .get("/api/bessj/3/5.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjForwardRecurrenceLargeX() {
        given()
            .when()
                .get("/api/bessj/3/10.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjBackwardRecurrence() {
        given()
            .when()
                .get("/api/bessj/10/5.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNegativeXOddN() {
        given()
            .when()
                .get("/api/bessj/3/-5.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNegativeXEvenN() {
        given()
            .when()
                .get("/api/bessj/4/-5.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjBackwardRecurrenceLargeN() {
        given()
            .when()
                .get("/api/bessj/100/10.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNegativeXEvenNLargeX() {
        given()
            .when()
                .get("/api/bessj/4/-10.0")
            .then()
                .statusCode(200);
    }
}