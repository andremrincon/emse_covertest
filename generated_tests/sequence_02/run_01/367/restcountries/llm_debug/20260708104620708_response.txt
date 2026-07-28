package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        io.restassured.RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowOriginHeader() {
        Response response = given().when().get("/v1/all").then().statusCode(404).extract().response();
        assertEquals(null, response.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsHeader() {
        Response response = given().when().get("/v1/all").then().statusCode(404).extract().response();
        assertEquals(null, response.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersHeader() {
        Response response = given().when().get("/v1/all").then().statusCode(404).extract().response();
        assertEquals(null, response.getHeader("Access-Control-Allow-Headers"));
    }

    @Test(timeout = 60000)
    public void testCacheControlHeader() {
        Response response = given().when().get("/v1/all").then().statusCode(404).extract().response();
        assertEquals(null, response.getHeader("Cache-Control"));
    }
}