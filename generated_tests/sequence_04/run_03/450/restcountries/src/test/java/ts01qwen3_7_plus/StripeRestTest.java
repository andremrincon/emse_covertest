package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StripeRestTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testContributeWithValidToken() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"token\": \"tok_valid_test\", \"amount\": 100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithNullContribution() {
        given()
            .contentType(ContentType.JSON)
            .body("null")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithBlankToken() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"token\": \"   \", \"amount\": 100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }
}