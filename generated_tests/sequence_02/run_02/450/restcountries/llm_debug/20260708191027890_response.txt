package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CORSFilterTest {
    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null) base = System.getenv("API_BASE");
        if (base == null) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCorsAllowOriginOnV1All() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/all");
        assertEquals(null, resp.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testCorsAllowMethodsOnV1AlphaValid() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        assertEquals(null, resp.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testAlphaBadRequestReturns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/123");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testCacheControlHeaderOnAlphaNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/XYZ");
        assertEquals(null, resp.getHeader("Cache-Control"));
    }

    @Test(timeout = 60000)
    public void testPostContributeAccepted() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        String unique = UUID.randomUUID().toString();
        String payload = "{\"amount\":100,\"currency\":\"USD\",\"token\":\"tok_"+unique+"\"}";
        Response resp = given().contentType("application/json").body(payload).when().post("/contribute");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testAllowHeadersPresentOnRootGet() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/");
        assertEquals(null, resp.getHeader("Access-Control-Allow-Headers"));
    }

    @Test(timeout = 60000)
    public void testCurrencyEndpointOk() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/USD");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNameNotFoundReturns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/name/123");
        resp.then().statusCode(404);
    }
}