package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CurrencyTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAlpha_US_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/{alphacode}", "US");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAlpha_invalid_alphacode_returns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/{alphacode}", "123");
        Assert.assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1_currency_USD_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/{currency}", "USD");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1_currency_notfound_returns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/{currency}", "XYZ");
        Assert.assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV2_currency_EUR_bodyContainsEuro() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/currency/{currency}", "EUR");
        Assert.assertTrue(resp.asString().contains("Euro"));
    }

    @Test(timeout = 60000)
    public void testName_fullText_true_returns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", "true").when().get("/v1/name/{name}", "France");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testContribute_post_returns202() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String id = UUID.randomUUID().toString();
        String body = "{\"amount\":100,\"currency\":\"USD\",\"token\":\"tok_" + id + "\"}";
        Response resp = given().contentType("application/json").body(body).when().post("/contribute");
        Assert.assertEquals(400, resp.getStatusCode());
    }
}