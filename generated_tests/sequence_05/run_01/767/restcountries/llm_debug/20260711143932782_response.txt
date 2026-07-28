package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StripeRestTest {

    @Test(timeout = 60000)
    public void testContributeWithBlankToken() {
        given()
                .contentType("application/json;charset=utf-8")
                .body("{\"token\": \"\", \"amount\": 100}")
                .when()
                .post("http://localhost:8080/rest/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithValidToken() {
        given()
                .contentType("application/json;charset=utf-8")
                .body("{\"token\": \"tok_123456789\", \"amount\": 500}")
                .when()
                .post("http://localhost:8080/rest/contribute")
                .then()
                .statusCode(400);
    }
}