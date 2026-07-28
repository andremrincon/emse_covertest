package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class ExpintTest {

    @BeforeClass
    public static void setUp() {
        String host = System.getenv().getOrDefault("API_HOST", "localhost");
        String port = System.getenv().getOrDefault("API_PORT", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
    }

    @Test(timeout = 60000)
    public void testExpintNZeroBranch() {
        given()
            .when()
                .get("/api/expint/0/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXZeroBranch() {
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
    public void testExpintSeriesNM1NotZero() {
        given()
            .when()
                .get("/api/expint/3/0.1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesNM1Zero() {
        given()
            .when()
                .get("/api/expint/1/0.1")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintErrorBranch() {
        given()
            .when()
                .get("/api/expint/-1/2.5")
            .then()
                .statusCode(400);
    }
}