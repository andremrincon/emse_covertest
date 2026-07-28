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
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost");
        RestAssured.port = Integer.parseInt(System.getProperty("port", "8080"));
        RestAssured.basePath = System.getProperty("basePath", "/rest");
    }

    @Test(timeout = 60000)
    public void contribute_withNullBody_returnsBadRequest() {
        given()
                .contentType(ContentType.JSON)
                .body("")
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void contribute_withBlankToken_returnsBadRequest() {
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
    public void contribute_withValidTokenButStripeFailure_returnsBadRequest() {
        String uniqueToken = "tok_" + java.util.UUID.randomUUID().toString().replace("-", "");
        String payload = "{\"token\":\"" + uniqueToken + "\",\"amount\":500}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/contribute")
                .then()
                .statusCode(400);
    }
}