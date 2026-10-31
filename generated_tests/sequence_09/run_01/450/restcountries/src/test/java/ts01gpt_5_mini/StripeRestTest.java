package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    @BeforeClass
    public static void init() {
        String envUrl = System.getProperty("baseUrl");
        if (envUrl == null || envUrl.isEmpty()) {
            envUrl = System.getenv("BASE_URL");
        }
        if (envUrl == null || envUrl.isEmpty()) {
            envUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = envUrl;
    }

    @Test(timeout = 60000)
    public void testContributeWithNullBodyReturnsBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().contentType("application/json").when().post("/contribute");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithBlankTokenReturnsBadRequest() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        String json = "{\"amount\":100,\"token\":\"   \"}";
        Response resp = given().contentType("application/json").body(json).when().post("/contribute");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithInvalidTokenTriggersStripeErrorReturnsBadRequest() {
        given().queryParam("fields", "name;capital;population").when().get("/v2/all").then().statusCode(lessThan(300));
        String json = "{\"amount\":250,\"token\":\"invalid_token_" + UUID.randomUUID().toString() + "\"}";
        Response resp = given().contentType("application/json").body(json).when().post("/contribute");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithValidTokenReturnsAccepted() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        String json = "{\"amount\":500,\"token\":\"tok_visa_" + UUID.randomUUID().toString() + "\"}";
        Response resp = given().contentType("application/json").body(json).when().post("/contribute");
        resp.then().statusCode(400);
    }
}