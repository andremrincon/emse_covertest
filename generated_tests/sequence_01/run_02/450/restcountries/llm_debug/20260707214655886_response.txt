package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null) {
            baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testContributeWithNullToken() {
        String payload = "{\"amount\": 100}";

        given()
            .contentType("application/json")
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeWithEmptyToken() {
        String payload = "{\"token\": \"\"}";

        given()
            .contentType("application/json")
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeWithValidToken() {
        String payload = "{\"token\": \"tok_123456789\", \"amount\": 500}";

        given()
            .contentType("application/json")
            .body(payload)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }
}