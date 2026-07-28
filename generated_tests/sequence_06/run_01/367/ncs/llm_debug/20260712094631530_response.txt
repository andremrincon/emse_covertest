package ts01qwen3_7_plus;

import org.junit.Before;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testExpintErrorCondition() {
        given()
            .when()
                .get("/api/expint/-1/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintContinuedFractionConverge() {
        given()
            .when()
                .get("/api/expint/3/2.5")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testExpintSeriesConverge() {
        given()
            .when()
                .get("/api/expint/1/0.1")
            .then()
                .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testExpintContinuedFractionFailed() {
        given()
            .when()
                .get("/api/expint/100/1.0001")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesFailed() {
        given()
            .when()
                .get("/api/expint/100/0.9999")
            .then()
                .statusCode(200);
    }
}