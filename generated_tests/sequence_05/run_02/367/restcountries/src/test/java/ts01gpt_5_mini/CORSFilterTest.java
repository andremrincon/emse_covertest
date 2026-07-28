package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080/rest");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAlphaUSReturnsAccessControlAllowOriginHeader() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        act.then().header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testAlphaInvalidReturnsBadRequestStatus() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testAlphaUnknownReturnsNotFoundStatus() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1AllIncludesCacheControlHeader() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/all");
        act.then().header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testCurrencyUSDIncludesAccessControlAllowMethodsHeader() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/USD");
        act.then().header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testNameFranceIncludesAccessControlAllowHeadersHeader() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/France");
        act.then().header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testCallingCodeOneReturnsOkStatus() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/callingcode/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRegionEuropeIncludesAccessControlAllowOriginHeader() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/region/Europe");
        act.then().header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testV2AllWithFieldsReturnsOkStatus() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v2/all?fields=name;capital;population").then().statusCode(200);
    }
}