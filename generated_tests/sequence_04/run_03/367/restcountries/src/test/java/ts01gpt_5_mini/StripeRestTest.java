package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void contributeWithNullBody_returnsBadRequest() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().contentType("application/json;charset=utf-8").body("null").when().post("/contribute").then().body(containsString("Bad Request"));
    }

    @Test(timeout = 60000)
    public void contributeWithEmptyToken_returnsBadRequest() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String json = "{\"amount\":100,\"token\":\"\"}";
        given().contentType("application/json;charset=utf-8").body(json).when().post("/contribute").then().body(containsString("Bad Request"));
    }

    @Test(timeout = 60000)
    public void contributeWithWhitespaceToken_returnsBadRequest() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String json = "{\"amount\":50,\"token\":\"   \"}";
        given().contentType("application/json;charset=utf-8").body(json).when().post("/contribute").then().body(containsString("Bad Request"));
    }

    @Test(timeout = 60000)
    public void contributeWithNonBlankToken_triggersStripeHandling_returnsBadRequest() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String token = "tok-" + UUID.randomUUID().toString();
        String json = "{\"amount\":200,\"token\":\"" + token + "\"}";
        given().contentType("application/json;charset=utf-8").body(json).when().post("/contribute").then().body(containsString("Bad Request"));
    }
}