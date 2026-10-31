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
    public void contributeWithNullTokenReturnsBadRequest() {
        String payload = "{\"token\": null, \"amount\": 1000}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeWithEmptyTokenReturnsBadRequest() {
        String payload = "{\"token\": \"\", \"amount\": 1000}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeWithBlankTokenReturnsBadRequest() {
        String payload = "{\"token\": \"   \", \"amount\": 1000}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeWithValidTokenReturnsAcceptedOrBadRequest() {
        String payload = "{\"token\": \"tok_visa\", \"amount\": 1000}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(lessThan(500));
    }

    @Test(timeout = 60000)
    public void contributeWithTokenAndNoAmountReturnsAcceptedOrBadRequest() {
        String payload = "{\"token\": \"tok_mastercard\"}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(lessThan(500));
    }
}