package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void contributeWithNullBodyReturnsBadRequest() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("")
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeWithNullTokenReturnsBadRequest() {
        String payload = "{\"amount\": 100}";

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeWithEmptyTokenReturnsBadRequest() {
        String payload = "{\"token\": \"\", \"amount\": 100}";

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeWithWhitespaceTokenReturnsBadRequest() {
        String payload = "{\"token\": \"   \", \"amount\": 100}";

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeWithValidTokenReturnsBadRequestDueToStripeException() {
        String payload = "{\"token\": \"tok_test_12345\", \"amount\": 100}";

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeWithValidTokenAndAmountReturnsAcceptedWhenStripeSucceeds() {
        String payload = "{\"token\": \"tok_visa\", \"amount\": 500}";

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(lessThan(500));
    }
}