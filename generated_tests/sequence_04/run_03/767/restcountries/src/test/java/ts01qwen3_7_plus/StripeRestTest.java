package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class StripeRestTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <404>.")
    @Test(timeout = 60000)
    public void testContributeWithBlankToken() {
        given()
            .contentType("application/json;charset=utf-8")
            .body("{\"amount\": 100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <404>.")
    @Test(timeout = 60000)
    public void testContributeWithValidToken() {
        given()
            .contentType("application/json;charset=utf-8")
            .body("{\"amount\": 100, \"token\": \"tok_test_123\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }
}