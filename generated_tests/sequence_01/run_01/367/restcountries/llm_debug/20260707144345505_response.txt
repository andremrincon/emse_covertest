package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StripeRestTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testContributeWithNullToken() {
        given()
            .contentType("application/json")
            .body("{\"amount\": 100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithBlankToken() {
        given()
            .contentType("application/json")
            .body("{\"amount\": 100, \"token\": \"   \"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithValidToken() {
        given()
            .contentType("application/json")
            .body("{\"amount\": 100, \"token\": \"tok_valid_test\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }
}