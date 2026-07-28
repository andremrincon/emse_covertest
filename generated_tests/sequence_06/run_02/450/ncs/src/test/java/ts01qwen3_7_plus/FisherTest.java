package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FisherTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testFisherBothOddNoLoopExecution() {
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
    public void testFisherBothOddWithLoopExecution() {
        given()
            .pathParam("m", 3)
            .pathParam("n", 3)
            .pathParam("x", 0.5)
        .when()
            .get("/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherOddMEvenNWithLoopExecution() {
        given()
            .pathParam("m", 3)
            .pathParam("n", 4)
            .pathParam("x", 0.5)
        .when()
            .get("/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherEvenMOddNBranch() {
        given()
            .pathParam("m", 4)
            .pathParam("n", 3)
            .pathParam("x", 0.5)
        .when()
            .get("/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherBothEvenBranch() {
        given()
            .pathParam("m", 4)
            .pathParam("n", 4)
            .pathParam("x", 0.5)
        .when()
            .get("/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherLargeXForUpperBoundCheck() {
        given()
            .pathParam("m", 10)
            .pathParam("n", 5)
            .pathParam("x", 1000.0)
        .when()
            .get("/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(200);
    }
}