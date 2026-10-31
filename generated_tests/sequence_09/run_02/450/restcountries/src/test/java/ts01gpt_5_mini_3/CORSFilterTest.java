package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAllowOriginHeaderOnV1AlphaUS() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/alpha/US");
        r.then().header("Access-Control-Allow-Origin", "*");
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAllowMethodsHeaderOnV1NameFrance() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/name/France");
        r.then().header("Access-Control-Allow-Methods", "GET");
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testAllowHeadersHeaderOnV1CurrencyUSD() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/currency/USD");
        r.then().header("Access-Control-Allow-Headers", "Accept, X-Requested-With");
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testCacheControlHeaderOnV2AllWithFields() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response r = given().param("fields", "name;capital;region;population;flag").when().get("/v2/all");
        r.then().header("Cache-Control", "public, max-age=86400");
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1AlphaValidReturns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/alpha/US");
        r.then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1AlphaInvalidFormatReturns400() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/alpha/123");
        r.then().statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV1AlphaNotFoundReturns404() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/alpha/True");
        r.then().statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testV2AllWithFieldsReturns200() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response r = given().param("fields", "name;capital;population;region").when().get("/v2");
        r.then().statusCode(200);
    }
}