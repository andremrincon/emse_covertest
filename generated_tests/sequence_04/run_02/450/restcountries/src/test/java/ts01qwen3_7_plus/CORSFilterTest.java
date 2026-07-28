package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CORSFilterTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowOriginHeader() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/all").then().statusCode(200).extract().response();
        assertEquals(null, response.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsHeader() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/all").then().statusCode(200).extract().response();
        assertEquals(null, response.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersHeader() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/all").then().statusCode(200).extract().response();
        assertEquals(null, response.getHeader("Access-Control-Allow-Headers"));
    }

    @Test(timeout = 60000)
    public void testCacheControlHeader() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().when().get("/v1/all").then().statusCode(200).extract().response();
        assertEquals(null, response.getHeader("Cache-Control"));
    }
}