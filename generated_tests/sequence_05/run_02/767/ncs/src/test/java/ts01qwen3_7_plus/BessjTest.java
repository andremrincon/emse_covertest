package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class BessjTest {

    private static final String BASE_URL = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testBessj_nLessThan2_returns400() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/bessj/1/2.5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_axLessThan8_axGreaterThanN_returns200() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/bessj/3/5.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_axGreaterThanOrEqual8_axGreaterThanN_returns200() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/bessj/3/10.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_axEquals0_returns200() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/bessj/3/0.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_axLessThanOrEqualN_returns200() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/bessj/10/5.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_xNegative_nOdd_returns200() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/bessj/3/-5.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_xNegative_nEven_returns200() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/bessj/4/-10.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_invalidN_returns400() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/bessj/abc/2.5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_invalidX_returns400() {
        given()
            .baseUri(BASE_URL)
        .when()
            .get("/api/bessj/3/abc")
        .then()
            .statusCode(400);
    }
}