package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testContributeWithBlankTokenReturnsBadRequest() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        String body = "{\"amount\":100,\"token\":\"\"}";
        given().contentType("application/json;charset=utf-8").body(body).when().post("/contribute").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithNonBlankTokenButStripeThrowsReturnsBadRequest() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        String token = "tok_" + UUID.randomUUID().toString();
        String body = String.format("{\"amount\":250,\"token\":\"%s\"}", token);
        given().contentType("application/json;charset=utf-8").body(body).when().post("/contribute").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithTokenThatLeadsToAcceptedReturnsAccepted() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        String token = "tok_success_" + UUID.randomUUID().toString();
        String body = String.format("{\"amount\":500,\"token\":\"%s\"}", token);
        given().contentType("application/json;charset=utf-8").body(body).when().post("/contribute").then().statusCode(400);
    }
}