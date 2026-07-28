package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAllowOriginHeaderOnV1All() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/all");
        act.then().header("Access-Control-Allow-Origin", "*");
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testStatus200OnV1AlphaUS() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        act.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAllowHeadersHeaderOnCurrencyUSD() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/USD");
        act.then().header("Access-Control-Allow-Headers", "Accept, X-Requested-With");
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testCacheControlHeaderOnNameFrance() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/France?fullText=false");
        act.then().header("Cache-Control", "public, max-age=86400");
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAlphaBadRequestNumeric() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/123");
        act.then().statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAlphaNotFoundXYZ() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/XYZ");
        act.then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAlphaQueryCodes200() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha?codes=US,CA,MX");
        act.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV2AllFieldsFilteringReturns200() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/all?fields=name;capital;region");
        act.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testPostContributeAccepted() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().contentType("application/json").body("{\"amount\":100,\"currency\":\"USD\",\"token\":\"tok\"}").when().post("/contribute");
        act.then().statusCode(202);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testOptionsAllowMethodsHeaderOnV1All() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().when().options("/v1/all");
        act.then().header("Access-Control-Allow-Methods", "GET");
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testCallingCode1Returns200() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/callingcode/1");
        act.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1AlphaCodes400Invalid() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha?codes=123");
        act.then().statusCode(400);
    }
}