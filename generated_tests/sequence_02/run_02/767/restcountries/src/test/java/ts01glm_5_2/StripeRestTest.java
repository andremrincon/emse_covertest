package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class StripeRestTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void contribute_nullBody_returnsBadRequest() {
        given()
                .contentType(ContentType.JSON)
                .body("")
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
    public void contribute_validToken_chargeExceptionReturnsBadRequest() {
        String body = "{\"amount\":1000,\"token\":\"tok_test_12345\"}";

        given()
                .contentType(ContentType.JSON)
                .body(body)
        .when()
                .post("/contribute")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contribute_validTokenWithAmount_chargeExceptionReturnsBadRequest() {
        String body = "{\"amount\":500,\"token\":\"tok_visa\"}";

        given()
                .contentType(ContentType.JSON)
                .body(body)
        .when()
                .post("/contribute")
        .then()
                .statusCode(lessThan(500));
    }
}