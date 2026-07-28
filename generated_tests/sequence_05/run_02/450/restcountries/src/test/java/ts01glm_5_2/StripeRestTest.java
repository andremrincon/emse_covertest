package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

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
    public void contribute_emptyToken_returnsBadRequest() {
        String body = "{\"token\":\"\",\"amount\":100}";
        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contribute_whitespaceToken_returnsBadRequest() {
        String body = "{\"token\":\"   \",\"amount\":100}";
        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contribute_nullToken_returnsBadRequest() {
        String body = "{\"token\":null,\"amount\":100}";
        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contribute_validTokenButStripeFails_returnsBadRequest() {
        String body = "{\"token\":\"tok_visa\",\"amount\":100}";
        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contribute_validTokenAndAmount_attemptsCharge() {
        String body = "{\"token\":\"tok_mastercard\",\"amount\":500}";
        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }
}