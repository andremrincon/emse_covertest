package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ResponseEntityTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("base.url", System.getenv().getOrDefault("BASE_URL", "http://localhost:8080/rest"));
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testResponseEntityStatus() {
        given()
        .when()
            .get("/v1/name/123")
        .then()
            .body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testResponseEntityMessage() {
        given()
        .when()
            .get("/v1/name/123")
        .then()
            .body("message", equalTo("Not Found"));
    }
}