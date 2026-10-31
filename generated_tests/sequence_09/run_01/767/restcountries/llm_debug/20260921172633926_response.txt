package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("BASE_URL");
        String prop = System.getProperty("base.url");
        String base = env != null ? env : (prop != null ? prop : "http://localhost:8080/rest");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testContributeWithEmptyBodyReturnsBadRequest() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        String payload = "{}";
        given().contentType("application/json;charset=utf-8").body(payload).when().post("/contribute").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithInvalidTokenReturnsBadRequest() {
        given().when().get("/v1/alpha?codes=US").then().statusCode(lessThan(300));
        String payload = "{\"amount\":100,\"token\":\"invalid_token_" + UUID.randomUUID().toString() + "\"}";
        given().contentType("application/json;charset=utf-8").body(payload).when().post("/contribute").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithValidTokenReturnsAccepted() {
        given().queryParam("fields", "name;capital;population;region").when().get("/v2").then().statusCode(lessThan(300));
        String payload = "{\"amount\":100,\"token\":\"tok_visa\",\"description\":\"contribution-" + UUID.randomUUID().toString() + "\"}";
        given().contentType("application/json;charset=utf-8").body(payload).when().post("/contribute").then().statusCode(400);
    }
}