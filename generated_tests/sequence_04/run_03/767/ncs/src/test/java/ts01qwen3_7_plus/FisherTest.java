package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class FisherTest {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testFisherValidParams() {
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
    public void testFisherOddParams() {
        given()
            .pathParam("m", 1)
            .pathParam("n", 1)
            .pathParam("x", 0.0)
        .when()
            .get(baseUrl + "/api/fisher/{m}/{n}/{x}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidM() {
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