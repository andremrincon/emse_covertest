package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testExpint_NegativeN() {
        given()
            .when()
                .get("/api/expint/-1/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_NegativeX() {
        given()
            .when()
                .get("/api/expint/3/-999.9")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_ZeroXAndZeroN() {
        given()
            .when()
                .get("/api/expint/0/0.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_XGreaterThan1() {
        given()
            .when()
                .get("/api/expint/3/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_XLessThanOrEqualTo1() {
        given()
            .when()
                .get("/api/expint/3/0.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_LoopFailure() {
        given()
            .when()
                .get("/api/expint/100/100.0")
            .then()
                .statusCode(200);
    }
}