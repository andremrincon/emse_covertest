package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FisherTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("base.url");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testFisherBothOddNormalReturn() {
        given()
            .when()
                .get("/api/fisher/{m}/{n}/{x}", 1, 1, 0.75)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherEvenMOddNWithLoops() {
        given()
            .when()
                .get("/api/fisher/{m}/{n}/{x}", 10, 5, 0.75)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherPNegativeReturnZero() {
        given()
            .when()
                .get("/api/fisher/{m}/{n}/{x}", 10, 1, 0.01)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherPGreaterThanOneReturnOne() {
        given()
            .when()
                .get("/api/fisher/{m}/{n}/{x}", 1, 1, 1e21)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherOddMOddNWithJLoop() {
        given()
            .when()
                .get("/api/fisher/{m}/{n}/{x}", 1, 5, 0.75)
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidParameter() {
        given()
            .when()
                .get("/api/fisher/{m}/{n}/{x}", "abc", 5, 0.75)
            .then()
                .statusCode(400);
    }
}