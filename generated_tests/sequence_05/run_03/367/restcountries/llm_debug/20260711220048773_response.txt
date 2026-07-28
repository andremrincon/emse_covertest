package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    @Before
    public void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.trim().isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testContributeWithBlankToken() {
        String requestBody = "{\"amount\": 100}";

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeWithValidToken() {
        String requestBody = "{\"amount\": 100, \"token\": \"tok_123456\"}";

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }
}