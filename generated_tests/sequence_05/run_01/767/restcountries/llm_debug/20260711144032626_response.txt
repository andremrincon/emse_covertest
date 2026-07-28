package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ResponseEntityTest {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : System.getProperty("baseUrl", "http://localhost:8080/rest");

    @Test(timeout = 60000)
    public void testGetStatusFromResponseEntity() {
        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/v1/name/123");

        response.then().body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testGetMessageFromResponseEntity() {
        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/v1/capital/123");

        response.then().body("message", equalTo("Not Found"));
    }
}