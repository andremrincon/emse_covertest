package ts01qwen3_7_plus_3;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    @Test(timeout = 60000)
    public void testContributeWithMissingToken() {
        given()
                .baseUri("http://localhost:8080/rest")
                .contentType("application/json;charset=utf-8")
                .body("{\"amount\": 100}")
                .when()
                .post("/contribute")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeWithValidToken() {
        given()
                .baseUri("http://localhost:8080/rest")
                .contentType("application/json;charset=utf-8")
                .body("{\"token\": \"tok_123\", \"amount\": 100}")
                .when()
                .post("/contribute")
                .then()
                .statusCode(404);
    }
}