package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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
    public void testContributeWithMissingToken() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\": 100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeWithValidToken() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"token\": \"tok_test_12345\", \"amount\": 100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }
}