package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testContribute_MissingToken_ReturnsBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String body = "{\"amount\":100}";
        Response act = given().contentType("application/json;charset=utf-8").body(body).when().post("/contribute");
        Assert.assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testContribute_InvalidToken_TriggersException_ReturnsBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String token = "invalid-" + UUID.randomUUID().toString();
        String body = "{\"amount\":250,\"token\":\"" + token + "\"}";
        Response act = given().contentType("application/json;charset=utf-8").body(body).when().post("/contribute");
        Assert.assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testContribute_ValidToken_ReturnsAccepted() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String token = "tok_visa";
        String unique = UUID.randomUUID().toString();
        String body = "{\"amount\":500,\"token\":\"" + token + "\",\"id\":\"" + unique + "\"}";
        Response act = given().contentType("application/json;charset=utf-8").body(body).when().post("/contribute");
        Assert.assertEquals(400, act.getStatusCode());
    }
}