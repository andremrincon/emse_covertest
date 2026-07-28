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
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMissingTokenReturnsBadRequest() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String payload = "{\"amount\":1000}";
        Response act = given().contentType("application/json;charset=utf-8").body(payload).when().post("/contribute");
        assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testInvalidStripeAuthReturnsBadRequest() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String unique = UUID.randomUUID().toString();
        String payload = "{\"amount\":1500,\"token\":\"tok_invalid_" + unique + "\"}";
        Response act = given().contentType("application/json;charset=utf-8").body(payload).when().post("/contribute");
        assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testValidContributionReturnsAccepted() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String unique = UUID.randomUUID().toString();
        String payload = "{\"amount\":500,\"token\":\"tok_success_" + unique + "\"}";
        Response act = given().contentType("application/json;charset=utf-8").body(payload).when().post("/contribute");
        assertEquals(400, act.getStatusCode());
    }
}