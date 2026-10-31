package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    @BeforeClass
    public static void setUp() {
        String base = System.getProperty("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testContributeWithNullBody_returnsBadRequest() {
        given().when().get("/v1/name/France").then().statusCode(lessThan(300));
        Response resp = given().contentType("application/json").when().post("/contribute");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithBlankToken_returnsBadRequest() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        String json = "{\"amount\":100,\"token\":\"\"}";
        Response resp = given().contentType("application/json").body(json).when().post("/contribute");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithWhitespaceToken_returnsBadRequest() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        String json = "{\"amount\":50,\"token\":\"   \"}";
        Response resp = given().contentType("application/json").body(json).when().post("/contribute");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContributeWithNonBlankToken_triggersStripeError_returnsBadRequest() {
        given().when().get("/v1/callingcode/1").then().statusCode(lessThan(300));
        String json = "{\"amount\":200,\"token\":\"tok_test_visa\"}";
        Response resp = given().contentType("application/json").body(json).when().post("/contribute");
        resp.then().statusCode(400);
    }
}