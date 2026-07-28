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
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void contribute_withNullBody_returnsBadRequest() {
        given()
                .contentType(ContentType.JSON)
                .body("{}")
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contribute_withBlankToken_returnsBadRequest() {
        String uniqueId = java.util.UUID.randomUUID().toString();
        String payload = "{\"token\":\"   \",\"amount\":100}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contribute_withValidToken_throwsStripeException_returnsBadRequest() {
        String uniqueId = java.util.UUID.randomUUID().toString();
        String payload = "{\"token\":\"tok_" + uniqueId + "\",\"amount\":500}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }
}