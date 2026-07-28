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
    public void contribute_withNullBody_returnsBadRequest() {
        given()
                .contentType(ContentType.JSON)
                .body("")
                .when()
                .post("/contribute")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_withBlankToken_returnsBadRequest() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"token\":\"  \",\"amount\":100}")
                .when()
                .post("/contribute")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void contribute_withToken_stripeChargeFails_returnsBadRequest() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"token\":\"tok_test_" + java.util.UUID.randomUUID().toString() + "\",\"amount\":500}")
                .when()
                .post("/contribute")
                .then()
                .statusCode(404);
    }
}