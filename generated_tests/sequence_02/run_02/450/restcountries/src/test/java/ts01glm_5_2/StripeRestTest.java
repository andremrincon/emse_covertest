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
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
        RestAssured.basePath = "/rest";
    }

    @Test(timeout = 60000)
    public void contribute_withNullContribution_returnsBadRequest() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contribute_withBlankToken_returnsBadRequest() {
        String body = "{\"token\":\"   \",\"amount\":1000}";
        given()
            .contentType(ContentType.JSON)
            .body(body)
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contribute_withValidToken_returnsBadRequestDueToStripeException() {
        String body = "{\"token\":\"tok_visa\",\"amount\":1000}";
        given()
            .contentType(ContentType.JSON)
            .body(body)
        .when()
            .post("/contribute")
        .then()
            .statusCode(400);
    }
}