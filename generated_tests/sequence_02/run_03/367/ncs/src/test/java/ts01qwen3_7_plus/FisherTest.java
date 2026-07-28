package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class FisherTest {

    private final String baseUrl = System.getProperty("baseUrl", System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080");

    @Test(timeout = 60000)
    public void testFisherMOddNEven() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/fisher/1/2/0.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherMEvenNOdd() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/fisher/2/1/0.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherMOddNOdd() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/fisher/1/1/0.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherMEvenNEven() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/fisher/2/2/0.5")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherPGreaterThanOne() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/fisher/100/100/1.0")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherPLessThanZero() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/api/fisher/1/100/0.0")
        .then()
            .statusCode(200);
    }
}