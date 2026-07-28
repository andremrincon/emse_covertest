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
        String payload = "{\"amount\":100,\"token\":null}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contribute_emptyToken_returnsBadRequest() {
        String payload = "{\"amount\":100,\"token\":\"\"}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contribute_whitespaceToken_returnsBadRequest() {
        String payload = "{\"amount\":100,\"token\":\"   \"}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contribute_validTokenButStripeFails_returnsBadRequest() {
        String payload = "{\"amount\":100,\"token\":\"tok_valid\"}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contribute_missingTokenField_returnsBadRequest() {
        String payload = "{\"amount\":100}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }
}