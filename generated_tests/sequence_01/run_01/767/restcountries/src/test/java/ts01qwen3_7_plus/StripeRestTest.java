package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class StripeRestTest {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";

    @Test(timeout = 60000)
    public void testContributeWithNullToken() {
        given()
            .contentType("application/json;charset=utf-8")
            .body("{}")
        .when()
            .post(baseUrl + "/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithEmptyToken() {
        given()
            .contentType("application/json;charset=utf-8")
            .body("{\"token\": \"\"}")
        .when()
            .post(baseUrl + "/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithValidToken() {
        given()
            .contentType("application/json;charset=utf-8")
            .body("{\"token\": \"tok_123456789\", \"amount\": 100}")
        .when()
            .post(baseUrl + "/contribute")
        .then()
            .statusCode(400);
    }
}