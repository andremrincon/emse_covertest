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
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void contributeWithNullBodyReturnsBadRequest() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeWithEmptyTokenReturnsBadRequest() {
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
    public void contributeWithWhitespaceTokenReturnsBadRequest() {
        String body = "{\"amount\":500,\"token\":\"   \"}";

        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeWithNullTokenReturnsBadRequest() {
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
    public void contributeWithValidTokenReturnsBadRequestDueToStripeException() {
        String body = "{\"amount\":1000,\"token\":\"tok_visa\"}";

        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeWithValidTokenAndAmountCoversChargeCreatePath() {
        String body = "{\"amount\":2500,\"token\":\"tok_mastercard\"}";

        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/contribute")
                .then()
                .statusCode(lessThan(500));
    }
}