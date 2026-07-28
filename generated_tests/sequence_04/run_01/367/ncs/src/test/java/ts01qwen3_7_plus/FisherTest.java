package ts01qwen3_7_plus;

import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FisherTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testFisherNormalCase() {
        given()
            .pathParam("m", 10)
            .pathParam("n", 5)
            .pathParam("x", 0.75)
        .when()
            .get(baseUrl + "/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherPNegative() {
        given()
            .pathParam("m", 10)
            .pathParam("n", 1)
            .pathParam("x", 0.00001)
        .when()
            .get(baseUrl + "/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherPGreaterThanOne() {
        given()
            .pathParam("m", 1)
            .pathParam("n", 100)
            .pathParam("x", 100000.0)
        .when()
            .get(baseUrl + "/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherA1B1NoLoops() {
        given()
            .pathParam("m", 1)
            .pathParam("n", 1)
            .pathParam("x", 0.75)
        .when()
            .get(baseUrl + "/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherA1B1WithLoops() {
        given()
            .pathParam("m", 3)
            .pathParam("n", 3)
            .pathParam("x", 0.75)
        .when()
            .get(baseUrl + "/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidInput() {
        given()
            .pathParam("m", "abc")
            .pathParam("n", 5)
            .pathParam("x", 0.75)
        .when()
            .get(baseUrl + "/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(400);
    }
}