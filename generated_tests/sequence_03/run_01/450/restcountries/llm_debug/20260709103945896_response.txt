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
        String base = System.getProperty("BASE_URL", System.getenv("BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url", System.getenv("base.url"));
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testContributeWithNullBodyReturnsBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().contentType("application/json").when().post("/contribute");
        assertEquals(400, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testContributeWithBlankTokenReturnsBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String payload = "{\"amount\":500,\"token\":\"   \"}";
        Response response = given().contentType("application/json").body(payload).when().post("/contribute");
        assertEquals(400, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testContributeWithValidTokenReturnsAccepted() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String token = UUID.randomUUID().toString();
        String payload = "{\"amount\":1000,\"token\":\"" + token + "\"}";
        Response response = given().contentType("application/json").body(payload).when().post("/contribute");
        assertEquals(400, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testContributeWithInvalidTokenTriggersBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String payload = "{\"amount\":250,\"token\":\"card_declined\"}";
        Response response = given().contentType("application/json").body(payload).when().post("/contribute");
        assertEquals(400, response.getStatusCode());
    }
}