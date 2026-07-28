package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080/rest";
        }
    }

    @Test(timeout = 60000)
    public void contribute_nullBody_returnsBadRequest() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contribute_nullToken_returnsBadRequest() {
        String body = "{\"amount\":1000,\"token\":null}";
        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contribute_emptyToken_returnsBadRequest() {
        String body = "{\"amount\":1000,\"token\":\"\"}";
        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contribute_blankToken_returnsBadRequest() {
        String body = "{\"amount\":1000,\"token\":\"   \"}";
        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contribute_validToken_attemptsChargeAndReturnsResponse() {
        String body = "{\"amount\":1000,\"token\":\"tok_test_" + java.util.UUID.randomUUID().toString() + "\"}";
        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/contribute")
                .then()
                .statusCode(org.hamcrest.Matchers.anyOf(
                        org.hamcrest.Matchers.is(202),
                        org.hamcrest.Matchers.is(400)
                ));
    }
}