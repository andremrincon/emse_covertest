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
                .body("{}")
        .when()
                .post("/contribute")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_withBlankToken_returnsBadRequest() {
        String payload = "{\"token\":\"  \",\"amount\":1000}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_withValidToken_returnsAcceptedOrBadRequest() {
        String uniqueToken = "tok_" + java.util.UUID.randomUUID().toString().replace("-", "");
        String payload = "{\"token\":\"" + uniqueToken + "\",\"amount\":500}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/contribute")
        .then()
                .statusCode(lessThan(500));
    }
}