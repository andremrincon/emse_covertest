package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
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
    public void testContributeNullPayload_returnsBadRequest() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        given().contentType(ContentType.JSON).when().post("/contribute").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeBlankToken_returnsBadRequest() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String payload = "{\"amount\":100,\"token\":\"\"}";
        given().contentType("application/json;charset=utf-8").body(payload).when().post("/contribute").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWhitespaceToken_returnsBadRequest() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String payload = "{\"amount\":50,\"token\":\"   \"}";
        given().contentType("application/json;charset=utf-8").body(payload).when().post("/contribute").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithToken_responseBodyContainsStatus() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String token = "tok_test_" + UUID.randomUUID().toString();
        String payload = "{\"amount\":250,\"token\":\"" + token + "\"}";
        given().contentType("application/json;charset=utf-8").body(payload).when().post("/contribute").then().body(containsString("status"));
    }
}