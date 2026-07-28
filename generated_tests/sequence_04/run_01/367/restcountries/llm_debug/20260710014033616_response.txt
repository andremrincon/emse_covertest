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
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testContributeWithNullContribution() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeWithNullToken() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\": 100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeWithWhitespaceToken() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"token\": \"   \", \"amount\": 100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeWithValidTokenButStripeError() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"token\": \"tok_valid123\", \"amount\": 100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(404);
    }
}