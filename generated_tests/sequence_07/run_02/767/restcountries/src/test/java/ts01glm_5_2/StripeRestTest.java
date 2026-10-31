package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;

public class StripeRestTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.basePath = System.getProperty("basePath", "/rest");
    }

    @Test(timeout = 60000)
    public void testContributeWithEmptyBody() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithNullToken() {
        given()
            .contentType(ContentType.JSON)
            .body("{}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithEmptyToken() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"token\":\"\",\"amount\":1000}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithWhitespaceToken() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"token\":\"   \",\"amount\":1000}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithValidTokenAndAmount() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"token\":\"tok_visa\",\"amount\":1000}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(anyOf(equalTo(202), equalTo(400)));
    }

    @Test(timeout = 60000)
    public void testContributeWithTokenNoAmount() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"token\":\"tok_mastercard\"}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(anyOf(equalTo(202), equalTo(400)));
    }
}