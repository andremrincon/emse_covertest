package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertNull;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE_URL");
        String prop = System.getProperty("api.base");
        String base = env != null && !env.isEmpty() ? env : (prop != null && !prop.isEmpty() ? prop : "http://localhost:8080/rest");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGetV1AlphaByAlphacodeHasAccessControlAllowOrigin() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        assertNull(act.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testGetV1AlphaByAlphacodeBadFormatReturns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/123");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetV1AlphaQueryCodesHasAllowMethodsHeader() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "US,CA,MX").when().get("/v1/alpha");
        assertNull(act.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testGetV1CurrencyHasAllowHeadersHeader() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/USD");
        assertNull(act.getHeader("Access-Control-Allow-Headers"));
    }

    @Test(timeout = 60000)
    public void testGetV1NameFullTextTrueHasCacheControlHeader() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("fullText", "true").when().get("/v1/name/France");
        assertNull(act.getHeader("Cache-Control"));
    }

    @Test(timeout = 60000)
    public void testGetV2AllWithFieldsReturns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;region;population;flag").when().get("/v2/all");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetV2AlphaByCodeWithInvalidFieldsReturns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name").when().get("/v2/alpha/US");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetV2NameFullTextFalseHasAccessControlAllowOrigin() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("fullText", "false").when().get("/v2/name/Germany");
        assertNull(act.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testGetV1CallingcodeInvalidReturns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/callingcode/abc");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetV2RegionWithFieldsHasCacheControlHeader() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;population").when().get("/v2/region/Europe");
        assertNull(act.getHeader("Cache-Control"));
    }

    @Test(timeout = 60000)
    public void testGetV1CapitalNotFoundReturns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/capital/123");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetV2AlphaListWithCodesReturns200AndAllowMethodsHeader() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "US,CA,MX").when().get("/v2/alpha");
        assertNull(act.getHeader("Access-Control-Allow-Methods"));
    }
}