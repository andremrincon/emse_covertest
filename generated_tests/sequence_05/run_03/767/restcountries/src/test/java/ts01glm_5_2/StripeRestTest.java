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
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
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
    public void contribute_validToken_returnsBadRequestDueToStripeException() {
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
    public void contribute_validTokenWithAmountAndCurrency_returnsBadRequestDueToStripeException() {
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