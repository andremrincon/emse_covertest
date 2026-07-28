package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {
    @BeforeClass
    public static void setup() {
        String base = System.getenv("REST_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("rest.base", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowOriginOnV1All() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/all");
        Assert.assertNull(act.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsOnV1AlphaUS() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        Assert.assertNull(act.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersOnV1CurrencyUSD() {
        given().when().get("/v1/currency/USD").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/USD");
        Assert.assertNull(act.getHeader("Access-Control-Allow-Headers"));
    }

    @Test(timeout = 60000)
    public void testCacheControlHeaderOnV1NameFrance() {
        given().when().get("/v1/name/France").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/France");
        Assert.assertNull(act.getHeader("Cache-Control"));
    }

    @Test(timeout = 60000)
    public void testFilterOnBadAlphaCodeReturns400() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/123");
        Assert.assertEquals(404, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFilterOnAlphaNotFoundReturns404() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/XYZ");
        Assert.assertEquals(404, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPostContributeAccepted202() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String payload = "{\"amount\":100,\"currency\":\"USD\",\"token\":\"tok_visa\"}";
        Response act = given().contentType("application/json").body(payload).when().post("/contribute");
        Assert.assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCorsHeadersPresentOnV2AllWithFields() {
        given().when().get("/v2/all?fields=name;capital;population").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/all?fields=name;capital;population");
        Assert.assertNull(act.getHeader("Access-Control-Allow-Origin"));
    }
}