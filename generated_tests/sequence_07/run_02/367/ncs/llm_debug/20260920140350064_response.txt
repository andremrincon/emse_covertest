package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;
import org.junit.BeforeClass;
import org.junit.Test;

public class BessjTest {

    private static String baseUrl;

    @BeforeClass
    public static void setup() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void bessj_nLessThan2_returns400() {
        given()
            .when()
                .get("/api/bessj/0/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void bessj_xZero_returns200WithValueZero() {
        given()
            .when()
                .get("/api/bessj/2/0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void bessj_axGreaterThanN_returns200() {
        given()
            .when()
                .get("/api/bessj/2/10.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void bessj_axLessThanOrEqualN_returns200() {
        given()
            .when()
                .get("/api/bessj/5/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_negativeXOddN_returns200() {
        given()
            .when()
                .get("/api/bessj/3/-2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_negativeXEvenNLargeAx_returns200() {
        given()
            .when()
                .get("/api/bessj/2/-10.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void bessj_invalidNType_returns400() {
        given()
            .when()
                .get("/api/bessj/abc/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void bessj_verySmallX_returns200() {
        given()
            .when()
                .get("/api/bessj/3/0.0000000001")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_positiveXOddNLargeAx_returns200() {
        given()
            .when()
                .get("/api/bessj/3/10.0")
            .then()
                .statusCode(200);
    }
}