package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testCorsFilterAllowOriginHeader() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        ValidatableResponse response = given().when().get("/v1/all").then().statusCode(200);
        response.header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void testCorsFilterAllowMethodsHeader() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        ValidatableResponse response = given().when().get("/v1/alpha/US").then().statusCode(200);
        response.header("Access-Control-Allow-Methods", equalTo(null));
    }

    @Test(timeout = 60000)
    public void testCorsFilterAllowHeadersHeader() {
        given().when().get("/v1/currency/USD").then().statusCode(lessThan(300));
        ValidatableResponse response = given().when().get("/v1/currency/USD").then().statusCode(200);
        response.header("Access-Control-Allow-Headers", equalTo(null));
    }

    @Test(timeout = 60000)
    public void testCorsFilterCacheControlHeader() {
        given().when().get("/v1/name/France").then().statusCode(lessThan(300));
        ValidatableResponse response = given().when().get("/v1/name/France").then().statusCode(200);
        response.header("Cache-Control", equalTo(null));
    }

    @Test(timeout = 60000)
    public void testCorsFilterChainDoFilterExecution() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        ValidatableResponse response = given().when().get("/v2/all").then();
        response.statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCorsFilterOnV2Endpoint() {
        given().when().get("/v2/alpha/US").then().statusCode(lessThan(300));
        ValidatableResponse response = given().when().get("/v2/alpha/US").then().statusCode(200);
        response.header("Access-Control-Allow-Origin", equalTo(null));
    }
}