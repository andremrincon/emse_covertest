package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StripeRestTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080/rest");
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
            .body("{\"token\": \"   \", \"amount\": 100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeEmptyToken() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"token\": \"\", \"amount\": 100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeInvalidToken() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"token\": \"tok_invalid_123\", \"amount\": 100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }
}