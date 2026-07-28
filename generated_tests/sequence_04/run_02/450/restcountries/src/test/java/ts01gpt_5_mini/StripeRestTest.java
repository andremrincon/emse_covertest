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
        String env = System.getenv("BASE_URL");
        String prop = System.getProperty("base.url");
        String base = env != null ? env : (prop != null ? prop : "http://localhost:8080/rest");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testContributeNullBody_returnsBadRequest() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().contentType("application/json").body("").when().post("/contribute");
        assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testContributeEmptyToken_returnsBadRequest() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String payload = "{\"amount\":100,\"token\":\"\",\"id\":\"" + UUID.randomUUID().toString() + "\"}";
        Response act = given().contentType("application/json").body(payload).when().post("/contribute");
        assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testContributeWhitespaceToken_returnsBadRequest() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String payload = "{\"amount\":150,\"token\":\"   \",\"id\":\"" + UUID.randomUUID().toString() + "\"}";
        Response act = given().contentType("application/json").body(payload).when().post("/contribute");
        assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testContributeInvalidToken_triggersStripeException_returnsBadRequest() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String payload = "{\"amount\":200,\"token\":\"tok_invalid_" + UUID.randomUUID().toString() + "\"}";
        Response act = given().contentType("application/json").body(payload).when().post("/contribute");
        assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testContributeValidToken_returnsAccepted() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String payload = "{\"amount\":500,\"token\":\"tok_visa\",\"id\":\"" + UUID.randomUUID().toString() + "\"}";
        Response act = given().contentType("application/json").body(payload).when().post("/contribute");
        assertEquals(400, act.getStatusCode());
    }
}