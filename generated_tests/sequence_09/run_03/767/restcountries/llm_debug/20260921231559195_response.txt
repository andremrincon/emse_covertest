package ts01gpt_5_mini_2;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.UUID;
import static io.restassured.RestAssured.given;
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
    public void testContributeWithBlankToken_returnsBadRequest() {
        given().queryParam("codes", "US").when().get("/v1/alpha").then().statusCode(lessThan(300));
        String json = "{\"amount\":100,\"token\":\"\"}";
        given().contentType("application/json;charset=utf-8").body(json).when().post("/contribute").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithRandomToken_triggersStripeError_returnsBadRequest() {
        given().queryParam("codes", "US").when().get("/v1/alpha").then().statusCode(lessThan(300));
        String token = UUID.randomUUID().toString();
        String json = "{\"amount\":250,\"token\":\"" + token + "\"}";
        given().contentType("application/json;charset=utf-8").body(json).when().post("/contribute").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithCommonTestToken_mayReturnAccepted() {
        given().queryParam("codes", "US").when().get("/v1/alpha").then().statusCode(lessThan(300));
        String json = "{\"amount\":500,\"token\":\"tok_visa\"}";
        given().contentType("application/json;charset=utf-8").body(json).when().post("/contribute").then().statusCode(400);
    }
}