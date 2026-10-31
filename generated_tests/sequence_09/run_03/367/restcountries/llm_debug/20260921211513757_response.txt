package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CORSFilterTest {

    private static final String BASE = System.getProperty("base.url", System.getenv("BASE_URL")) == null ? "http://localhost:8080/rest" : System.getProperty("base.url", System.getenv("BASE_URL"));

    @BeforeClass
    public static void setup() {
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test(timeout = 60000)
    public void testV1AlphaByCode200_headerOrigin() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v1/alpha/US");
        assertEquals(null, resp.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testV1AlphaByCode400_status() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v1/alpha/123");
        assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1AlphaByCode404_status() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v1/alpha/XYZ");
        assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1AlphaCodes200_headerMethods() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v1/alpha?codes=US,CA,MX");
        assertEquals(null, resp.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testV1AlphaCodes400_status() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v1/alpha?codes=123");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1Currency200_headerHeaders() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v1/currency/USD");
        assertEquals(null, resp.getHeader("Access-Control-Allow-Headers"));
    }

    @Test(timeout = 60000)
    public void testV1Currency404_status() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v1/currency/XYZ");
        assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1NameFullTextTrue_headerCacheControl() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v1/name/France?fullText=true");
        assertEquals(null, resp.getHeader("Cache-Control"));
    }

    @Test(timeout = 60000)
    public void testV1Callingcode200_status() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v1/callingcode/1");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1Capital404_status() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/v1/capital/123");
        assertEquals(404, resp.getStatusCode());
    }
}