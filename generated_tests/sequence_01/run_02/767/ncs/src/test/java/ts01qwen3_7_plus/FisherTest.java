package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FisherTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testFisherMOddNOdd() {
        given()
            .when()
            .get("/api/fisher/1/1/0.75")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherMOddNEven() {
        given()
            .when()
            .get("/api/fisher/1/2/0.75")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherMEvenNOdd() {
        given()
            .when()
            .get("/api/fisher/10/5/0.75")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherMEvenNEven() {
        given()
            .when()
            .get("/api/fisher/10/10/0.75")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherZeroX() {
        given()
            .when()
            .get("/api/fisher/1/1/0.0")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherLargeX() {
        given()
            .when()
            .get("/api/fisher/10/10/100.0")
            .then()
            .statusCode(200);
    }
}