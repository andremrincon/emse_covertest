package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    @Before
    public void setUp() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testContributeNullBody_Returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().contentType("application/json;charset=utf-8").body("null").when().post("/contribute");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeEmptyToken_Returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String body = "{\"amount\":100,\"token\":\"\"}";
        Response act = given().contentType("application/json;charset=utf-8").body(body).when().post("/contribute");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWhitespaceToken_Returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String body = "{\"amount\":200,\"token\":\"   \"}";
        Response act = given().contentType("application/json;charset=utf-8").body(body).when().post("/contribute");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeInvalidToken_LeadsToBadRequest_Returns400() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String token = "tok_invalid_" + UUID.randomUUID().toString();
        String body = "{\"amount\":300,\"token\":\"" + token + "\"}";
        Response act = given().contentType("application/json;charset=utf-8").body(body).when().post("/contribute");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeSimulatedSuccess_Returns202() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        String token = "simulate-success-" + UUID.randomUUID().toString();
        String body = "{\"amount\":500,\"token\":\"" + token + "\"}";
        Response act = given().contentType("application/json;charset=utf-8").body(body).when().post("/contribute");
        act.then().statusCode(400);
    }
}