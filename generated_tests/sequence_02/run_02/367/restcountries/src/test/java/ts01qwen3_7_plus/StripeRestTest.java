package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testContributeNullContribution() {
        given()
            .contentType("application/json;charset=utf-8")
            .body("null")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeNullToken() {
        given()
            .contentType("application/json;charset=utf-8")
            .body("{\"token\": null, \"amount\": 100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeBlankToken() {
        given()
            .contentType("application/json;charset=utf-8")
            .body("{\"token\": \"   \", \"amount\": 100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeValidToken() {
        given()
            .contentType("application/json;charset=utf-8")
            .body("{\"token\": \"tok_test_123\", \"amount\": 100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }
}