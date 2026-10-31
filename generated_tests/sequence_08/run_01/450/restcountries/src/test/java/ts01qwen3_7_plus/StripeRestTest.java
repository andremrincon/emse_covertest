package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StripeRestTest {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");

    @Test(timeout = 60000)
    public void testContributeWithNullToken() {
        given()
            .contentType("application/json;charset=utf-8")
            .body("{\"amount\": 100}")
        .when()
            .post(baseUrl + "/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithEmptyToken() {
        given()
            .contentType("application/json;charset=utf-8")
            .body("{\"amount\": 100, \"token\": \"\"}")
        .when()
            .post(baseUrl + "/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithBlankToken() {
        given()
            .contentType("application/json;charset=utf-8")
            .body("{\"amount\": 100, \"token\": \"   \"}")
        .when()
            .post(baseUrl + "/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithValidToken() {
        given()
            .contentType("application/json;charset=utf-8")
            .body("{\"amount\": 100, \"token\": \"tok_123456789\"}")
        .when()
            .post(baseUrl + "/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithNullContribution() {
        given()
            .contentType("application/json;charset=utf-8")
        .when()
            .post(baseUrl + "/contribute")
        .then()
            .statusCode(400);
    }
}