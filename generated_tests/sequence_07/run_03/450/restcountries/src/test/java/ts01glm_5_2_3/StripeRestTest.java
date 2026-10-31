package ts01glm_5_2_3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StripeRestTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void contributeWithNullTokenReturnsBadRequest() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"amount\":100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeWithBlankTokenReturnsBadRequest() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"token\":\"   \",\"amount\":100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contributeWithValidTokenReturnsBadRequestFromStripeException() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"token\":\"tok_12345\",\"amount\":100}")
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }
}