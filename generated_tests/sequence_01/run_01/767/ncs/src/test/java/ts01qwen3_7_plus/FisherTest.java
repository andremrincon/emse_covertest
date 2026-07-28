package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class FisherTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testFisher_a1_b1() {
        given()
            .pathParam("m", 1)
            .pathParam("n", 1)
            .pathParam("x", 0.5)
        .when()
            .get("/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_a1_bNot1() {
        given()
            .pathParam("m", 1)
            .pathParam("n", 2)
            .pathParam("x", 0.5)
        .when()
            .get("/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_pLessThan0() {
        given()
            .pathParam("m", 10)
            .pathParam("n", 5)
            .pathParam("x", 0.000001)
        .when()
            .get("/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_pGreaterThan1() {
        given()
            .pathParam("m", 10)
            .pathParam("n", 5)
            .pathParam("x", 100000.0)
        .when()
            .get("/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_pBetween0And1() {
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
    public void testFisher_invalidParameter() {
        given()
            .pathParam("m", "abc")
            .pathParam("n", 5)
            .pathParam("x", 0.75)
        .when()
            .get("/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(400);
    }
}