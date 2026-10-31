package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CORSFilterTest {

    @BeforeClass
    public static void setUp() {
        String url = System.getProperty("TEST_BASE_URL");
        if (url == null || url.isEmpty()) {
            url = System.getenv("TEST_BASE_URL");
        }
        if (url == null || url.isEmpty()) {
            url = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = url;
    }

    @Test(timeout = 60000)
    public void testCorsHeadersOnV1AlphaByCodeSuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/alpha/US");
        assertEquals(null, res.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testCorsHeadersOnV1AlphaByCodeBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/alpha/123");
        assertEquals(404, res.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCorsAllowMethodsHeaderOnV1AlphaQuerySuccess() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/alpha?codes=US,CA");
        assertEquals(null, res.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testCacheControlHeaderOnV1AlphaQueryWithArrayLikeCodes() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/alpha?codes=%5B%22US%22,%20%22CA%22%5D");
        assertEquals(null, res.getHeader("Cache-Control"));
    }

    @Test(timeout = 60000)
    public void testCorsAllowHeadersOnV1CurrencySuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/currency/USD");
        assertEquals(null, res.getHeader("Access-Control-Allow-Headers"));
    }

    @Test(timeout = 60000)
    public void testCorsHeadersOnV1NameFullTextTrue() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().when().get("/v1/name/France?fullText=true");
        assertEquals(null, res.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testCorsAllowMethodsOnV2AlphaWithFields() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response res = given().when().get("/v2/alpha/US?fields=name;capital;population");
        assertEquals(null, res.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testCorsHeadersOnRootPost() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response res = given().body("{}").when().post("/");
        assertEquals(null, res.getHeader("Access-Control-Allow-Origin"));
    }
}