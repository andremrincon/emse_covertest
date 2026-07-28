package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testContributeWithNullContribution() {
        given()
            .contentType("application/json;charset=utf-8")
            .body("null")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeWithNullToken() {
        given()
            .contentType("application/json;charset=utf-8")
            .body("{\"amount\": 100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeWithBlankToken() {
        given()
            .contentType("application/json;charset=utf-8")
            .body("{\"amount\": 100, \"token\": \"   \"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeWithValidToken() {
        given()
            .contentType("application/json;charset=utf-8")
            .body("{\"amount\": 100, \"token\": \"tok_valid_123\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }
}