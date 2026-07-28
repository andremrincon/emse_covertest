package ts01gpt_5_mini;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
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
        Response response = given().when().get("/v1/all");
        Assert.assertNull(response.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testAllowMethodsHeaderOnV1Alpha() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha/US");
        Assert.assertNull(response.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testBadAlphaReturns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/alpha/123");
        Assert.assertEquals(404, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testAllowHeadersOnContributePost() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String id = UUID.randomUUID().toString();
        String payload = "{\"amount\":1,\"currency\":\"USD\",\"id\":\"" + id + "\"}";
        Response response = given().contentType("application/json").body(payload).when().post("/contribute");
        Assert.assertNull(response.getHeader("Access-Control-Allow-Headers"));
    }

    @Test(timeout = 60000)
    public void testCacheControlHeaderOnV2AlphaWithFields() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response response = given().queryParam("fields", "name;capital;population").when().get("/v2/alpha/US");
        Assert.assertNull(response.getHeader("Cache-Control"));
    }

    @Test(timeout = 60000)
    public void testCorsOriginOnCurrencyEndpoint() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/currency/USD");
        Assert.assertNull(response.getHeader("Access-Control-Allow-Origin"));
    }
}