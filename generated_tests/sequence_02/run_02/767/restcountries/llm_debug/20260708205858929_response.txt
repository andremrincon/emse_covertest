package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class CORSFilterTest {
    private static final String BASE = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : System.getProperty("baseUrl", "http://localhost:8080/rest");

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowOriginOnV1All() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        given().when().get("/v1/all").then().header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsOnV1AlphaUS() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/{code}", "US").then().header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersOnV1AlphaInvalid() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/{code}", "123").then().header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCacheControlHeaderOnV1AlphaNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/{code}", "XYZ").then().header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnCurrencyEndpoint() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/{currency}", "USD").then().header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSAllowMethodsOnNameFullTextTrue() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("fullText", "true").when().get("/v1/name/{name}", "France").then().header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnPostRoot() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().contentType("application/json").body("{\"dummy\":\"value\"}").when().post("/").then().header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCORSOnV2AllWithFields() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().queryParam("fields", "name;capital;population;region").when().get("/v2/all").then().header("Cache-Control", nullValue());
    }
}