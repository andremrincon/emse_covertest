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
    public static void setUp() {
        String base = System.getProperty("base.uri");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URI");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMissingContributionReturnsBadRequest_1() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().contentType("application/json;charset=utf-8").body("{}").when().post("/contribute");
        assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBlankTokenReturnsBadRequest_2() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String payload = "{\"amount\":100,\"token\":\"   \"}";
        Response act = given().contentType("application/json;charset=utf-8").body(payload).when().post("/contribute");
        assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testNonEmptyTokenLeadsToStripeErrorReturnsBadRequest_3() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String token = "tok-" + UUID.randomUUID().toString();
        String payload = "{\"amount\":150,\"token\":\"" + token + "\"}";
        Response act = given().contentType("application/json;charset=utf-8").body(payload).when().post("/contribute");
        assertEquals(400, act.getStatusCode());
    }
}