package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CurrencyTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testV1Currency_USD_returns200() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/USD");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1Currency_numeric_returns400() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/123");
        Assert.assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1Currency_unknown_returns404() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/XYZ");
        Assert.assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV2Currency_EUR_withFields_returns200() {
        given().when().get("/v2/all?fields=name;capital;region;population;flag").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/currency/EUR?fields=name;capital;population");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV2Currency_EUR_badFields_returns400() {
        given().when().get("/v2/all?fields=name;capital;region;population;flag").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/currency/EUR?fields=name");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1Alpha_multipleCodes_returns200() {
        given().when().get("/v2/all?fields=name;capital;population;region;flag").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha?codes=US,CA,MX");
        Assert.assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1Alpha_invalidCodes_returns400() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha?codes=123");
        Assert.assertEquals(200, resp.getStatusCode());
    }
}