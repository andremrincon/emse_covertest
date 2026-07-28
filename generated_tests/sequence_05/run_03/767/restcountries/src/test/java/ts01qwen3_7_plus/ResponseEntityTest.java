package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ResponseEntityTest {

    private final String baseUrl = System.getProperty("test.base.url", "http://localhost:8080/rest");

    @Test(timeout = 60000)
    public void testGetStatus() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/name/123")
        .then()
            .body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testGetMessage() {
        given()
            .baseUri(baseUrl)
        .when()
            .get("/v1/name/123")
        .then()
            .body("message", equalTo("Not Found"));
    }
}