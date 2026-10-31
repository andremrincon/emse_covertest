package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StripeRestTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testContributeWithNullContribution() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithNullToken() {
        String body = "{\"amount\": 1000}";
        given()
            .contentType(ContentType.JSON)
            .body(body)
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithEmptyToken() {
        String body = "{\"token\": \"\", \"amount\": 1000}";
        given()
            .contentType(ContentType.JSON)
            .body(body)
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithWhitespaceToken() {
        String body = "{\"token\": \"   \", \"amount\": 1000}";
        given()
            .contentType(ContentType.JSON)
            .body(body)
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithValidTokenThrowsStripeException() {
        String body = "{\"token\": \"tok_test123\", \"amount\": 1000}";
        given()
            .contentType(ContentType.JSON)
            .body(body)
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }
}