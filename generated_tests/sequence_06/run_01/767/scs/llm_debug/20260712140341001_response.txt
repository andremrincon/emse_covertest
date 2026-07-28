package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class NotyPevarTest {

    private String getBaseUrl() {
        String envUrl = System.getenv("BASE_URL");
        return (envUrl != null && !envUrl.isEmpty()) ? envUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testNotyPevar_ValidInput_Status200() {
        String baseUrl = getBaseUrl();
        Response response = given().baseUri(baseUrl).when().get("/api/notypevar/28/world");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevar_AnotherValidInput_Status200() {
        String baseUrl = getBaseUrl();
        Response response = given().baseUri(baseUrl).when().get("/api/notypevar/7/hello");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevar_InvalidInteger_Status400() {
        String baseUrl = getBaseUrl();
        Response response = given().baseUri(baseUrl).when().get("/api/notypevar/abc/some-string");
        response.then().statusCode(400);
    }
}