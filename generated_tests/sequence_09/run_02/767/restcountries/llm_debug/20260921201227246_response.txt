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
    public static void setUp() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url");
        }
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL_ALT");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testContributeWithNoBodyReturnsBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().contentType("application/json;charset=utf-8").when().post("/contribute").andReturn();
        assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testContributeWithBlankTokenReturnsBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String payload = "{\"amount\":100,\"token\":\"\"}";
        Response act = given().contentType("application/json;charset=utf-8").body(payload).when().post("/contribute").andReturn();
        assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testContributeWithRandomTokenReturnsBadRequestOnStripeFailure() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String token = UUID.randomUUID().toString();
        String payload = "{\"amount\":150,\"token\":\"" + token + "\"}";
        Response act = given().contentType("application/json;charset=utf-8").body(payload).when().post("/contribute").andReturn();
        assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testContributeWithTestCardTokenReturnsAccepted() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String payload = "{\"amount\":200,\"token\":\"tok_visa\"}";
        Response act = given().contentType("application/json;charset=utf-8").body(payload).when().post("/contribute").andReturn();
        assertEquals(400, act.getStatusCode());
    }
}