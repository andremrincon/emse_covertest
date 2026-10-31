package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StripeRestTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testContributeWithBlankToken() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"token\": \"\", \"amount\": 100}")
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithValidToken() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"token\": \"tok_123456789\", \"amount\": 100}")
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }
}