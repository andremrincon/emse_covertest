package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testFisherNormalCaseReturnsValue() {
        given()
            .pathParam("m", 10)
            .pathParam("n", 5)
            .pathParam("x", 0.75)
        .when()
            .get("/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherReturnsZeroWhenPNegative() {
        given()
            .pathParam("m", 10)
            .pathParam("n", 2)
            .pathParam("x", 0.01)
        .when()
            .get("/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherReturnsOneWhenPGreaterThanOne() {
        given()
            .pathParam("m", 10)
            .pathParam("n", 3)
            .pathParam("x", 100)
        .when()
            .get("/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidMReturns400() {
        given()
            .pathParam("m", "abc")
            .pathParam("n", 5)
            .pathParam("x", 0.75)
        .when()
            .get("/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidNReturns400() {
        given()
            .pathParam("m", 10)
            .pathParam("n", -3)
            .pathParam("x", 0.75)
        .when()
            .get("/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidXReturns400() {
        given()
            .pathParam("m", 10)
            .pathParam("n", 5)
            .pathParam("x", 1.2)
        .when()
            .get("/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(200);
    }
}