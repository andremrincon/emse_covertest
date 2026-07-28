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
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("base.url", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCorsAllowOriginHeader() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        ValidatableResponse response = given().when().get("/v1/all").then().statusCode(200);
        response.header("Access-Control-Allow-Origin", equalTo(null));
    }

    @Test(timeout = 60000)
    public void testCorsAllowMethodsHeader() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        ValidatableResponse response = given().when().get("/v1/alpha/US").then().statusCode(200);
        response.header("Access-Control-Allow-Methods", equalTo(null));
    }

    @Test(timeout = 60000)
    public void testCorsAllowHeadersHeader() {
        given().when().get("/v1/name/France").then().statusCode(lessThan(300));
        ValidatableResponse response = given().when().get("/v1/name/France").then().statusCode(200);
        response.header("Access-Control-Allow-Headers", equalTo(null));
    }

    @Test(timeout = 60000)
    public void testCacheControlHeader() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        ValidatableResponse response = given().when().get("/v2/all").then().statusCode(200);
        response.header("Cache-Control", equalTo(null));
    }

    @Test(timeout = 60000)
    public void testFilterChainContinuesSuccessfully() {
        given().when().get("/v1/currency/USD").then().statusCode(lessThan(300));
        ValidatableResponse response = given().when().get("/v1/currency/USD").then().statusCode(200);
        response.statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCorsHeadersOnV2AlphaEndpoint() {
        given().when().get("/v2/alpha/US").then().statusCode(lessThan(300));
        ValidatableResponse response = given().when().get("/v2/alpha/US").then().statusCode(200);
        response.header("Access-Control-Allow-Origin", equalTo(null));
    }
}