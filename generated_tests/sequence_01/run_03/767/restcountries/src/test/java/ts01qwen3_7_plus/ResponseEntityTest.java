package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ResponseEntityTest {

    private String getBaseUrl() {
        String envUrl = System.getenv("BASE_URL");
        return (envUrl != null && !envUrl.isEmpty()) ? envUrl : "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testResponseEntityMessageOnV1NameNotFound() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/v1/name/123")
        .then()
            .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testResponseEntityStatusOnV1NameNotFound() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/v1/name/123")
        .then()
            .body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testResponseEntityMessageOnV2CapitalNotFound() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/v2/capital/12345")
        .then()
            .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testResponseEntityStatusOnV2CapitalNotFound() {
        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/v2/capital/12345")
        .then()
            .body("status", equalTo(404));
    }
}