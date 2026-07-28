package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StripeRestTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost");
        RestAssured.port = Integer.parseInt(System.getProperty("base.port", "8080"));
        RestAssured.basePath = System.getProperty("base.path", "/rest");
    }

    @Test(timeout = 60000)
    public void testContributeWithNullBody() {
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
            .body("{\"amount\":1000}")
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
    public void testContributeWithValidToken() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"token\":\"tok_visa\",\"amount\":1000}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }
}