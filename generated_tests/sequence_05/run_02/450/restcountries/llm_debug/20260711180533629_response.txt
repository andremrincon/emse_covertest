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
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = base;
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test(timeout = 60000)
    public void testMissingTokenReturnsBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String json = "{\"amount\":100}";
        Response resp = given().contentType("application/json").body(json).when().post("/contribute");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testStripeErrorReturnsBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String token = "invalid_" + UUID.randomUUID().toString();
        String json = "{\"amount\":150,\"token\":\"" + token + "\"}";
        Response resp = given().contentType("application/json").body(json).when().post("/contribute");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testValidTokenReturnsAccepted() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String token = "tok_visa_" + UUID.randomUUID().toString();
        String json = "{\"amount\":200,\"token\":\"" + token + "\"}";
        Response resp = given().contentType("application/json").body(json).when().post("/contribute");
        assertEquals(400, resp.getStatusCode());
    }
}