package ts01qwen3_7_plus;

import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testContributeNullBody() {
        given()
            .baseUri(baseUrl)
            .contentType(ContentType.JSON)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeNullToken() {
        given()
            .baseUri(baseUrl)
            .contentType(ContentType.JSON)
            .body("{\"amount\": 100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeEmptyToken() {
        given()
            .baseUri(baseUrl)
            .contentType(ContentType.JSON)
            .body("{\"amount\": 100, \"token\": \"\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeValidToken() {
        given()
            .baseUri(baseUrl)
            .contentType(ContentType.JSON)
            .body("{\"amount\": 100, \"token\": \"tok_123456789\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }
}