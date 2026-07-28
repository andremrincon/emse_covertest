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
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testBessj_nLessThan2() {
        given()
            .when()
                .get("/api/bessj/1/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_axGreaterThanN_positiveX() {
        given()
            .when()
                .get("/api/bessj/3/10.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_axGreaterThanN_negativeX() {
        given()
            .when()
                .get("/api/bessj/3/-10.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_axLessThanOrEqualToN() {
        given()
            .when()
                .get("/api/bessj/3/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_axIsZero() {
        given()
            .when()
                .get("/api/bessj/3/0.0")
            .then()
                .statusCode(200);
    }
}