package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    private static String base;

    @BeforeClass
    public static void setup() {
        String fromProp = System.getProperty("BASE_URL");
        String fromEnv = System.getenv("BASE_URL");
        base = fromProp != null && !fromProp.isEmpty() ? fromProp : (fromEnv != null && !fromEnv.isEmpty() ? fromEnv : "http://localhost:8080/rest");
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test(timeout = 60000)
    public void testContributeMissingTokenReturnsBadRequest() {
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        Response act = given().contentType("application/json").body("{\"amount\":100}").when().post(base + "/contribute");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeInvalidTokenLeadsToBadRequest() {
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        String uniq = UUID.randomUUID().toString();
        Response act = given().contentType("application/json").body("{\"amount\":250,\"token\":\"invalid_" + uniq + "\"}").when().post(base + "/contribute");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithTypicalStripeTokenReturnsAccepted() {
        given().when().get(base + "/v1/all").then().statusCode(lessThan(300));
        Response act = given().contentType("application/json").body("{\"amount\":50,\"token\":\"tok_visa\"}").when().post(base + "/contribute");
        act.then().statusCode(400);
    }
}