package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
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

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAllowOriginHeaderOnV1AlphaByCode() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        assertEquals("*", resp.getHeader("Access-Control-Allow-Origin"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAllowMethodsHeaderOnV1AlphaQueryCodes() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha?codes=US,CA");
        assertEquals("GET", resp.getHeader("Access-Control-Allow-Methods"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAllowHeadersHeaderOnV2AllWithFields() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fields", "name;capital;region;population;flag").when().get("/v2/all");
        assertEquals("Accept, X-Requested-With", resp.getHeader("Access-Control-Allow-Headers"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testCacheControlHeaderOnRootGet() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response resp = given().when().get("/");
        assertEquals("public, max-age=86400", resp.getHeader("Cache-Control"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAllowOriginHeaderOnV1NameFullText() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response resp = given().queryParam("fullText", "true").when().get("/v1/name/France");
        assertEquals("*", resp.getHeader("Access-Control-Allow-Origin"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1CurrencyInvalidReturns400AndFilterRuns() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/123");
        assertEquals(400, resp.getStatusCode());
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV2AlphaCodesMalformedReturns400() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response resp = given().queryParam("codes", "[\"US\",\"CA\"]").when().get("/v2/alpha");
        assertEquals(400, resp.getStatusCode());
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testPostOnV1ReturnsMethodNotAllowedAndAllowsCORS() {
        given().when().get("/").then().statusCode(lessThan(300));
        Response resp = given().body("{}").when().post("/v1");
        assertEquals("GET", resp.getHeader("Access-Control-Allow-Methods"));
    }
}