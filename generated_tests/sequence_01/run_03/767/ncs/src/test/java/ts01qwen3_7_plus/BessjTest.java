package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class BessjTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
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
    public void testBessjXGreaterThanN() {
        given()
            .when()
            .get("/api/bessj/3/10.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNegativeXOddN() {
        given()
            .when()
            .get("/api/bessj/3/-10.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNegativeXEvenN() {
        given()
            .when()
            .get("/api/bessj/4/-10.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjXLessThanN() {
        given()
            .when()
            .get("/api/bessj/10/2.5")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNegativeXLessThanN() {
        given()
            .when()
            .get("/api/bessj/10/-2.5")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjInvalidN() {
        given()
            .when()
            .get("/api/bessj/1/2.5")
            .then()
            .statusCode(400);
    }
}