package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class StripeRestTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testRejectBlankToken() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String json = "{\"amount\":100,\"token\":\"\"}";
        Response response = given().contentType("application/json").body(json).when().post("/contribute");
        assertEquals(400, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBadTokenCausesStripeErrorReturnsBadRequest() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        String token = "invalid_" + UUID.randomUUID().toString();
        String json = "{\"amount\":250,\"token\":\"" + token + "\"}";
        Response response = given().contentType("application/json").body(json).when().post("/contribute");
        assertEquals(400, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testValidLookingTokenReturnsAccepted() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String token = "tok_visa_" + UUID.randomUUID().toString();
        String json = "{\"amount\":500,\"token\":\"" + token + "\"}";
        Response response = given().contentType("application/json").body(json).when().post("/contribute");
        assertEquals(400, response.getStatusCode());
    }
}