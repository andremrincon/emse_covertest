package ts01qwen3_7_plus_3;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StripeRestTest {

    @Test(timeout = 60000)
    public void testContributeWithMissingToken() {
        String payload = "{\"amount\": 100}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithInvalidToken() {
        String payload = "{\"amount\": 100, \"token\": \"tok_invalid\"}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(400);
    }
}