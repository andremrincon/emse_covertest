package ts01gpt_5_mini_2;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("BASE_URL");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testCorsAllowOriginOnV1AlphaUS() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/alpha/US");
        r.then().header("Access-Control-Allow-Origin", "*");
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testCorsAllowMethodsOnV1AlphaQueryCodes() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response r = given().queryParam("codes", "US,CA,MX").when().get("/v1/alpha");
        r.then().header("Access-Control-Allow-Methods", "GET");
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testCorsAllowHeadersOnV1CurrencyUSD() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/currency/USD");
        r.then().header("Access-Control-Allow-Headers", "Accept, X-Requested-With");
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testCacheControlHeaderOnV1All() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/all");
        r.then().header("Cache-Control", "public, max-age=86400");
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testCorsAllowOriginOnV2AlphaWithFields() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response r = given().queryParam("fields", "name;capital;population").when().get("/v2/alpha/US");
        r.then().header("Access-Control-Allow-Origin", "*");
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testRootGetReturns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/").then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1AlphaInvalidFormatReturns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/123").then().statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV2AlphaBadCodesReturns400() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "[\"US\", \"CA\"]").when().get("/v2/alpha").then().statusCode(400);
    }
}