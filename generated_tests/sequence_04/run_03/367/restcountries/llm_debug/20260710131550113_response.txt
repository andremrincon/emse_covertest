package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StripeRestTest {

    @Test(timeout = 60000)
    public void testContributeWithNullToken() {
        Response response = given()
                .contentType("application/json;charset=utf-8")
                .body("{}")
                .when()
                .post("http://localhost:8080/rest/contribute");

        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithBlankToken() {
        Response response = given()
                .contentType("application/json;charset=utf-8")
                .body("{\"token\": \"   \"}")
                .when()
                .post("http://localhost:8080/rest/contribute");

        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithValidToken() {
        Response response = given()
                .contentType("application/json;charset=utf-8")
                .body("{\"token\": \"tok_123\", \"amount\": 100}")
                .when()
                .post("http://localhost:8080/rest/contribute");

        response.then().statusCode(400);
    }
}