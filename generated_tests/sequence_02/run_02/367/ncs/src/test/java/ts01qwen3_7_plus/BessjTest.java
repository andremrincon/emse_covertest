package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testBessj_nLessThan2_returns400() {
        given()
            .when()
                .get("/api/bessj/1/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_xEqualsZero_returns200() {
        given()
            .when()
                .get("/api/bessj/3/0.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_axGreaterThanN_smallX_returns200() {
        given()
            .when()
                .get("/api/bessj/3/5.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_axGreaterThanN_largeX_returns200() {
        given()
            .when()
                .get("/api/bessj/3/10.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_axLessThanOrEqualToN_returns200() {
        given()
            .when()
                .get("/api/bessj/10/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_xNegativeNOddLargeX_returns200() {
        given()
            .when()
                .get("/api/bessj/3/-10.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_invalidN_returns400() {
        given()
            .when()
                .get("/api/bessj/abc/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_largeNSmallX_returns200() {
        given()
            .when()
                .get("/api/bessj/50/1.0")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_invalidX_returns400() {
        given()
            .when()
                .get("/api/bessj/3/abc")
            .then()
                .statusCode(400);
    }
}