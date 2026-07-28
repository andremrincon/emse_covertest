package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StripeRestTest {

    @Test(timeout = 60000)
    public void testContributeWithValidToken() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");

        Response response = given()
                .contentType("application/json;charset=utf-8")
                .body("{\"token\": \"tok_valid_123\", \"amount\": 100}")
                .when()
                .post(baseUrl + "/contribute");

        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithBlankToken() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");

        Response response = given()
                .contentType("application/json;charset=utf-8")
                .body("{\"token\": \"\", \"amount\": 100}")
                .when()
                .post(baseUrl + "/contribute");

        response.then().statusCode(400);
    }
}