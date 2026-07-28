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
    public void contributeWithBlankTokenReturnsBadRequest() {
        String payload = "{\"amount\":1000,\"token\":\"   \"}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeWithNullBodyReturnsBadRequest() {
        given()
                .contentType(ContentType.JSON)
                .body("{}")
        .when()
                .post("/contribute")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeWithValidTokenReturnsBadRequestDueToStripeException() {
        String payload = "{\"amount\":1000,\"token\":\"tok_visa\"}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(400);
    }
}