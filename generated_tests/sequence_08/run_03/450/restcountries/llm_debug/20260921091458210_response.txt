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
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testContributeWithWhitespaceToken() {
        String payload = "{\"token\": \"   \"}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeWithNullToken() {
        String payload = "{}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeWithValidTokenFormat() {
        String payload = "{\"token\": \"tok_123456789\", \"amount\": 100}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(404);
    }
}