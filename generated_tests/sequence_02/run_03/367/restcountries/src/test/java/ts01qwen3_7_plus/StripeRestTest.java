package ts01qwen3_7_plus;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StripeRestTest {

    @Test(timeout = 60000)
    public void testContributeWithNullToken() {
        given()
            .contentType(ContentType.JSON)
            .body("{}")
        .when()
            .post("http://localhost:8080/rest/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithEmptyToken() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"token\": \"\"}")
        .when()
            .post("http://localhost:8080/rest/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithBlankToken() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"token\": \"   \"}")
        .when()
            .post("http://localhost:8080/rest/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithValidToken() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"token\": \"tok_123456789\", \"amount\": 100}")
        .when()
            .post("http://localhost:8080/rest/contribute")
        .then()
            .statusCode(400);
    }
}