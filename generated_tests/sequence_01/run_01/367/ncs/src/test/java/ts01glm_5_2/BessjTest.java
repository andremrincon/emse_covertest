package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BessjTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testBessjNZeroReturns400() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/bessj/0/2.5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjNOneReturns400() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/bessj/1/2.5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjXZeroReturns200() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/bessj/3/0.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxLessThanOrEqualNReturns200() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/bessj/3/1.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanNSmallXReturns200() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/bessj/3/5.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNegativeSmallXOddNReturns200() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/bessj/3/-5.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjLargeXReturns200() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/bessj/3/10.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjLargeNegativeXOddNReturns200() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/bessj/3/-10.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjLargeNegativeXEvenNReturns200() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/bessj/4/-10.0")
        .then()
            .statusCode(200);
    }
}