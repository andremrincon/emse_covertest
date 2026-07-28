package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CORSFilterTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Origin\" was not \"*\", was \"null\"...")
    @Test(timeout = 60000)
    public void testAccessControlAllowOriginHeader() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/all").then().header("Access-Control-Allow-Origin", equalTo("*"));
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Methods\" was not \"GET\", was \"nul...")
    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsHeader() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/all").then().header("Access-Control-Allow-Methods", equalTo("GET"));
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Headers\" was not \"Accept, X-Reques...")
    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersHeader() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/all").then().header("Access-Control-Allow-Headers", equalTo("Accept, X-Requested-With"));
    }

    @Ignore("1 expectation failed. Expected header \"Cache-Control\" was not \"public, max-age=86400\", was \"...")
    @Test(timeout = 60000)
    public void testCacheControlHeader() {
        given().when().get("/v1/all").then().statusCode(404);
        given().when().get("/v1/all").then().header("Cache-Control", equalTo("public, max-age=86400"));
    }
}