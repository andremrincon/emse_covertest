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
    public void testBessj_NLessThan2_Returns400() {
        given()
            .when()
                .get("/api/bessj/-5/2.5")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_XIsZero_Returns200() {
        given()
            .when()
                .get("/api/bessj/2/0.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_AxGreaterThanNAndAxGreaterThan8_Returns200() {
        given()
            .when()
                .get("/api/bessj/2/10.0")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessj_AxLessThanOrEqualToNAndAxLessThan8_Returns200() {
        given()
            .when()
                .get("/api/bessj/3/2.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_AxGreaterThanNAndAxLessThan8_Returns200() {
        given()
            .when()
                .get("/api/bessj/2/5.0")
            .then()
                .statusCode(400);
    }
}