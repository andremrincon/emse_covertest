package ts01qwen3_7_plus_2;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StripeRestTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");

    @Test(timeout = 60000)
    public void testContributeWithBlankToken() {
        given()
            .baseUri(baseUrl)
            .contentType(ContentType.JSON)
            .body("{\"token\": \"\", \"amount\": 100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithValidToken() {
        given()
            .baseUri(baseUrl)
            .contentType(ContentType.JSON)
            .body("{\"token\": \"tok_test_123\", \"amount\": 100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }
}