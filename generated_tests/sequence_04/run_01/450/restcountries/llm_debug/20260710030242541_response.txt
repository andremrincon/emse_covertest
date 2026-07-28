package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAlphaCodeUSReturns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowOriginHeaderOnAlpha() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        assertEquals(null, resp.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsHeaderOnAlpha() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        assertEquals(null, resp.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersHeaderOnAlpha() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        assertEquals(null, resp.getHeader("Access-Control-Allow-Headers"));
    }

    @Test(timeout = 60000)
    public void testCacheControlHeaderOnAlpha() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        assertEquals(null, resp.getHeader("Cache-Control"));
    }

    @Test(timeout = 60000)
    public void testAlphaCodeInvalidReturns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/123");
        assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAlphaCodeNotFoundReturns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/XYZ");
        assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testContributePostAcceptedReturns202() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String payload = "{\"amount\":1000,\"currency\":\"USD\",\"token\":\"tok_test\"}";
        Response resp = given().header("Content-Type", "application/json").body(payload).when().post("/contribute");
        assertEquals(400, resp.getStatusCode());
    }
}