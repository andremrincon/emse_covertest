package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    private String baseUrl() {
        String b = System.getProperty("baseUrl");
        if (b != null && !b.isEmpty()) return b;
        b = System.getenv("BASE_URL");
        if (b != null && !b.isEmpty()) return b;
        return "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testContribute_NullBody_ReturnsBadRequest() {
        String base = baseUrl();
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().contentType("application/json;charset=utf-8").body("").when().post(base + "/contribute");
        Assert.assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testContribute_BlankToken_ReturnsBadRequest() {
        String base = baseUrl();
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        String json = "{\"amount\":100,\"token\":\"   \"}";
        Response resp = given().contentType("application/json;charset=utf-8").body(json).when().post(base + "/contribute");
        Assert.assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testContribute_WithToken_TriggersStripeError_ReturnsBadRequest() {
        String base = baseUrl();
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        String token = UUID.randomUUID().toString();
        String json = "{\"amount\":250,\"token\":\"" + token + "\"}";
        Response resp = given().contentType("application/json;charset=utf-8").body(json).when().post(base + "/contribute");
        Assert.assertEquals(400, resp.getStatusCode());
    }
}