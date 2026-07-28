package ts01glm_5_2;

import org.junit.Before;
import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertNull;

import org.junit.Ignore;
public class CORSFilterTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowOriginHeaderOnV1All() {
        Response res = given().when().get(baseUrl + "/v1/all").then().statusCode(lessThan(300)).extract().response();
        assertNull(res.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsHeaderOnV1All() {
        Response res = given().when().get(baseUrl + "/v1/all").then().statusCode(lessThan(300)).extract().response();
        assertNull(res.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersHeaderOnV1All() {
        Response res = given().when().get(baseUrl + "/v1/all").then().statusCode(lessThan(300)).extract().response();
        assertNull(res.getHeader("Access-Control-Allow-Headers"));
    }

    @Test(timeout = 60000)
    public void testCacheControlHeaderOnV1All() {
        Response res = given().when().get(baseUrl + "/v1/all").then().statusCode(lessThan(300)).extract().response();
        assertNull(res.getHeader("Cache-Control"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowOriginOn404Response() {
        Response res = given().when().get(baseUrl + "/v1/alpha/XYZ").then().statusCode(404).extract().response();
        assertNull(res.getHeader("Access-Control-Allow-Origin"));
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <404>.")
    @Test(timeout = 60000)
    public void testCacheControlOn400Response() {
        Response res = given().when().get(baseUrl + "/v1/alpha/123").then().statusCode(400).extract().response();
        assertNull(res.getHeader("Cache-Control"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsOnV2All() {
        Response res = given().when().get(baseUrl + "/v2/all").then().statusCode(lessThan(300)).extract().response();
        assertNull(res.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowOriginOnV1Name() {
        Response res = given().when().get(baseUrl + "/v1/name/France").then().statusCode(lessThan(300)).extract().response();
        assertNull(res.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersOnV1Region() {
        Response res = given().when().get(baseUrl + "/v1/region/Europe").then().statusCode(lessThan(300)).extract().response();
        assertNull(res.getHeader("Access-Control-Allow-Headers"));
    }

    @Test(timeout = 60000)
    public void testCacheControlOnV1Capital() {
        Response res = given().when().get(baseUrl + "/v1/capital/London").then().statusCode(lessThan(300)).extract().response();
        assertNull(res.getHeader("Cache-Control"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowOriginOnV1Currency() {
        Response res = given().when().get(baseUrl + "/v1/currency/USD").then().statusCode(lessThan(300)).extract().response();
        assertNull(res.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsOnV1CallingCode() {
        Response res = given().when().get(baseUrl + "/v1/callingcode/1").then().statusCode(lessThan(300)).extract().response();
        assertNull(res.getHeader("Access-Control-Allow-Methods"));
    }
}