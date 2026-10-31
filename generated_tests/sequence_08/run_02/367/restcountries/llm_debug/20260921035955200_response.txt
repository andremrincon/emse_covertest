package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.trim().isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
            RestAssured.basePath = "/rest";
        }
    }

    @Test(timeout = 60000)
    public void testGetStatus() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/name/123");
        response.then().body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testGetMessage() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v2/capital/12345");
        response.then().body("message", equalTo("Not Found"));
    }
}