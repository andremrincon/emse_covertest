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
    public void contribute_withNullBody_returnsBadRequest() {
        given()
                .contentType(ContentType.JSON)
        .when()
                .post("/contribute")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contribute_withEmptyToken_returnsBadRequest() {
        String payload = "{\"token\":\"\",\"amount\":1000}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contribute_withWhitespaceOnlyToken_returnsBadRequest() {
        String payload = "{\"token\":\"   \",\"amount\":1000}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contribute_withNullToken_returnsBadRequest() {
        String payload = "{\"token\":null,\"amount\":1000}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contribute_withValidTokenAndAmount_returnsBadRequestDueToStripeException() {
        String payload = "{\"token\":\"tok_visa\",\"amount\":1000}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(400);
    }
}