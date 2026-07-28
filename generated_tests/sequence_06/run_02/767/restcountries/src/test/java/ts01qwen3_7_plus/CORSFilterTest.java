package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class CORSFilterTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testCorsAllowOriginHeader() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/all").then().statusCode(200).header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsAllowMethodsHeader() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().statusCode(200).header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsAllowHeadersHeader() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/France").then().statusCode(200).header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCorsCacheControlHeader() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v2/all").then().statusCode(200).header("Cache-Control", nullValue());
    }
}