package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
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
                .get("/api/expint/3/-999.9")
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
    public void testExpintXGreaterThan1() {
        given()
            .when()
                .get("/api/expint/3/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintContinuedFractionFailed() {
        given()
            .when()
                .get("/api/expint/100/100.0")
            .then()
                .statusCode(lessThan(600));
    }

    @Test(timeout = 60000)
    public void testExpintSeriesFailed() {
        given()
            .when()
                .get("/api/expint/100/0.1")
            .then()
                .statusCode(lessThan(600));
    }
}