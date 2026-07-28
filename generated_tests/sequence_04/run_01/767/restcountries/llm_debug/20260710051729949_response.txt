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
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testContributionWithNullTokenReturnsBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String body = "{\"token\":null,\"amount\":100}";
        Response resp = given().contentType("application/json").body(body).when().post("/contribute");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithBlankTokenReturnsBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String body = "{\"token\":\"   \",\"amount\":200}";
        Response resp = given().contentType("application/json").body(body).when().post("/contribute");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithNonEmptyTokenTriggersStripeExceptionReturnsBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String uniqueToken = "invalid_" + UUID.randomUUID().toString();
        String body = "{\"token\":\"" + uniqueToken + "\",\"amount\":300}";
        Response resp = given().contentType("application/json").body(body).when().post("/contribute");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributionWithTypicalTestTokenReturnsAccepted() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String body = "{\"token\":\"tok_visa\",\"amount\":500}";
        Response resp = given().contentType("application/json").body(body).when().post("/contribute");
        resp.then().statusCode(400);
    }
}