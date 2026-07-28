package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StripeRestTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testContributeNullBody() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeNullToken() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\": 100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeBlankToken() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\": 100, \"token\": \"   \"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeValidTokenStripeFails() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\": 100, \"token\": \"tok_123456789\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }
}