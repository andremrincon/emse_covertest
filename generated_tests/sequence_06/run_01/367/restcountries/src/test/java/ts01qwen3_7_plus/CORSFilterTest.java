package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class CORSFilterTest {

    private final String baseUrl = System.getProperty("test.base.url", "http://localhost:8080/rest");

    @Test(timeout = 60000)
    public void testAccessControlAllowOriginHeader() {
        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/v1/all");

        assertEquals(null, response.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsHeader() {
        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/v1/alpha/US");

        assertEquals(null, response.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersHeader() {
        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/v1/name/France");

        assertEquals(null, response.getHeader("Access-Control-Allow-Headers"));
    }

    @Test(timeout = 60000)
    public void testCacheControlHeader() {
        Response response = given()
                .baseUri(baseUrl)
                .when()
                .get("/v1/capital/London");

        assertEquals(null, response.getHeader("Cache-Control"));
    }
}