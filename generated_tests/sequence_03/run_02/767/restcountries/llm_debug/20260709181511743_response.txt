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
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAllowOriginHeaderOnV1All() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/all");
        assertEquals(null, resp.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testAllowMethodsHeaderOnV1AlphaUS() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        assertEquals(null, resp.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testAllowHeadersHeaderOnV1AlphaMultiple() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha?codes=US,CA");
        assertEquals(null, resp.getHeader("Access-Control-Allow-Headers"));
    }

    @Test(timeout = 60000)
    public void testCacheControlHeaderOnV1CurrencyUSD() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/USD");
        assertEquals(null, resp.getHeader("Cache-Control"));
    }

    @Test(timeout = 60000)
    public void testDoFilterChainStillReturns200ForV1All() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/all");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testBadAlphaCodeReturns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/123");
        assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testNotFoundAlphaReturns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String unique = "XYZ" + UUID.randomUUID().toString().substring(0, 4);
        Response resp = given().when().get("/v1/alpha/" + unique);
        assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testRootGetHasCORSHeaders() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/");
        assertEquals(null, resp.getHeader("Access-Control-Allow-Origin"));
    }
}