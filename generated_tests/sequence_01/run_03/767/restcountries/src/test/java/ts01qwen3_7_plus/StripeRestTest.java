package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StripeRestTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testContributeNullContribution() {
        given()
            .contentType("application/json")
            .body("null")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeBlankToken() {
        given()
            .contentType("application/json")
            .body("{\"token\": \"   \", \"amount\": 100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeNullToken() {
        given()
            .contentType("application/json")
            .body("{\"token\": null, \"amount\": 100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeValidTokenFormat() {
        given()
            .contentType("application/json")
            .body("{\"token\": \"tok_123456789\", \"amount\": 500}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }
}