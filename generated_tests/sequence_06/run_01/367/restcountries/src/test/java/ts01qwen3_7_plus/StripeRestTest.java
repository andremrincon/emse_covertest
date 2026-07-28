package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    @Test(timeout = 60000)
    public void testContributeWithBlankToken() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";
        RestAssured.baseURI = baseUrl;

        String payload = "{\"token\": \"\", \"amount\": 100}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeWithValidToken() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";
        RestAssured.baseURI = baseUrl;

        String payload = "{\"token\": \"tok_123456789\", \"amount\": 100}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(404);
    }
}