package ts01gpt_5_mini_2;

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
    public static void init() {
        String base = System.getProperty("test.base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("TEST_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void contribute_withNoBody_returns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().contentType("application/json").when().post("/contribute");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void contribute_withBlankToken_returns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String payload = "{\"amount\":100,\"token\":\"   \"}";
        Response resp = given().contentType("application/json").body(payload).when().post("/contribute");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void contribute_withMissingToken_returns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String payload = "{\"amount\":100}";
        Response resp = given().contentType("application/json").body(payload).when().post("/contribute");
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void contribute_withToken_triggersStripePath_returns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String token = UUID.randomUUID().toString();
        String payload = "{\"amount\":123,\"token\":\"" + token + "\"}";
        Response resp = given().contentType("application/json").body(payload).when().post("/contribute");
        assertEquals(400, resp.getStatusCode());
    }
}