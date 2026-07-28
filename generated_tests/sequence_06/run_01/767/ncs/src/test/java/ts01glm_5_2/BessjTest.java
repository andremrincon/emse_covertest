package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BessjTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testBessjNLessThan2() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/bessj/1/2.5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjXZero() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/bessj/3/0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjForwardRecurrenceSmallAx() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/bessj/3/5.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjForwardRecurrenceLargeAx() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/bessj/3/10.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjForwardRecurrenceLargeAxNegativeX() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/bessj/3/-10.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjBackwardRecurrence() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/bessj/3/2.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjBackwardRecurrenceNegativeX() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/bessj/3/-2.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjForwardRecurrenceNegativeXEvenN() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/bessj/4/-5.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjBackwardRecurrenceLargeN() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/bessj/50/1.0")
        .then()
            .statusCode(200);
    }
}