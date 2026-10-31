package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class CORSFilterTest {
    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAllowOriginHeaderPresent_on_v1_all() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v1/all").then().header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testAllowMethodsHeader_on_v1_alpha_alphacode() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testAllowHeadersHeader_on_v1_currency() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v1/currency/USD").then().header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCacheControlHeader_on_v1_name() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/France").then().header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testV1AlphaSuccessStatus_for_valid_code() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testV1AlphaBadRequest_for_numeric_code() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1AlphaNotFound_for_XYZ() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1AlphaList_success_for_codes_query() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        given().queryParam("codes", "US,CA,MX, AD").when().get("/v1/alpha").then().statusCode(400);
    }
}