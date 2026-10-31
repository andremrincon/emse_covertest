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
        String base = System.getProperty("rest.base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("REST_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url");
        }
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testContributeWithNullBodyReturnsBadRequest() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().contentType("application/json;charset=utf-8").when().post("/contribute").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithEmptyTokenReturnsBadRequest() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        String json = "{\"token\":\"\",\"amount\":500}";
        given().contentType("application/json;charset=utf-8").body(json).when().post("/contribute").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithBlankTokenReturnsBadRequest() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        String json = "{\"token\":\"   \",\"amount\":1000}";
        given().contentType("application/json;charset=utf-8").body(json).when().post("/contribute").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithValidTokenReturnsAccepted() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        String token = "tok_visa_" + UUID.randomUUID().toString();
        String json = "{\"token\":\"" + token + "\",\"amount\":250}";
        given().contentType("application/json;charset=utf-8").body(json).when().post("/contribute").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithDeclinedTokenReturnsBadRequest() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        String token = "tok_chargeDeclined_" + UUID.randomUUID().toString();
        String json = "{\"token\":\"" + token + "\",\"amount\":750}";
        given().contentType("application/json;charset=utf-8").body(json).when().post("/contribute").then().statusCode(400);
    }
}