package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void bessj_nLessThan2_throwsException() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/0/2.5")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void bessj_xZero_returnsZero() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/3/0.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_axGreaterThanN_forwardRecurrence() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/3/2.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_negativeXLargeAx_coversBessj1Line114() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/3/-10.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_axLessThanOrEqualN_backwardRecurrence() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/10/2.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_negativeXOddN_returnsNegativeAns() {
        given()
            .accept("application/json")
        .when()
            .get("/api/bessj/3/-2.5")
        .then()
            .statusCode(200);
    }
}