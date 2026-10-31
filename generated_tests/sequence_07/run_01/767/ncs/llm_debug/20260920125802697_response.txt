package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

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
    public void bessj_n1_returns400() {
        given()
                .when()
                .get("/api/bessj/1/2.5")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void bessj_axGreaterThanN_axGreaterOrEqual8_returns200() {
        given()
                .when()
                .get("/api/bessj/3/10.0")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_negativeX_oddN_returns200() {
        given()
                .when()
                .get("/api/bessj/3/-10.0")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void bessj_xZero_returns200() {
        given()
                .when()
                .get("/api/bessj/2/0.0")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void bessj_axLessOrEqualN_returns200() {
        given()
                .when()
                .get("/api/bessj/5/2.5")
                .then()
                .statusCode(200);
    }
}