package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testExpintN0Branch() {
        given()
            .when()
                .get("/api/expint/0/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintNegativeNThrowsError() {
        given()
            .when()
                .get("/api/expint/-1/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintXZeroNPositive() {
        given()
            .when()
                .get("/api/expint/3/0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintContinuedFractionConvergence() {
        given()
            .when()
                .get("/api/expint/3/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesPath() {
        given()
            .when()
                .get("/api/expint/3/0.1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXZeroNOneThrowsError() {
        given()
            .when()
                .get("/api/expint/1/0")
            .then()
                .statusCode(400);
    }
}