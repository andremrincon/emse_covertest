package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ExpintTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testExpintNegativeN() {
        given()
            .when()
            .get("/api/expint/-1/2.5")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintNegativeX() {
        given()
            .when()
            .get("/api/expint/3/-1.0")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintZeroXAndZeroN() {
        given()
            .when()
            .get("/api/expint/0/0.0")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintXGreaterThanOne() {
        given()
            .when()
            .get("/api/expint/3/2.5")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintNEqualsOne() {
        given()
            .when()
            .get("/api/expint/1/0.5")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintNGreaterThanOneXLessThanOne() {
        given()
            .when()
            .get("/api/expint/3/0.5")
            .then()
            .statusCode(200);
    }
}