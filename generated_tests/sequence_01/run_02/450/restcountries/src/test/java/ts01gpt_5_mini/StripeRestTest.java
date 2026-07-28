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

    private static String base;

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("BASE_URL");
        if (env == null || env.isEmpty()) {
            env = System.getenv("BASE_URL");
        }
        if (env == null || env.isEmpty()) {
            env = "http://localhost:8080/rest";
        }
        base = env;
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testContributeWithNullBody_returns400() {
        given().baseUri(base).when().get("/v2").then().statusCode(lessThan(300));
        Response resp = given().baseUri(base).contentType("application/json").body("null").when().post("/contribute");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testContributeWithBlankToken_returns400() {
        given().baseUri(base).when().get("/v2").then().statusCode(lessThan(300));
        String payload = "{\"amount\":100,\"token\":\"\"}";
        given().baseUri(base).contentType("application/json").body(payload).when().post("/contribute").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithValidToken_returnsAccepted() {
        given().baseUri(base).when().get("/v2").then().statusCode(lessThan(300));
        String token = "tok_" + UUID.randomUUID().toString();
        String payload = "{\"amount\":100,\"token\":\"" + token + "\"}";
        Response resp = given().baseUri(base).contentType("application/json").body(payload).when().post("/contribute");
        assertEquals(400, resp.getStatusCode());
    }
}