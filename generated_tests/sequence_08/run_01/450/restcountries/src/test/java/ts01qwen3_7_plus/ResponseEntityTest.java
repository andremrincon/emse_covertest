package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testGetMessage() {
        given()
            .pathParam("name", "123")
        .when()
            .get("/v1/name/{name}")
        .then()
            .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testGetStatus() {
        given()
            .pathParam("name", "True")
            .queryParam("fullText", "yes")
        .when()
            .get("/v1/name/{name}")
        .then()
            .body("status", equalTo(404));
    }
}