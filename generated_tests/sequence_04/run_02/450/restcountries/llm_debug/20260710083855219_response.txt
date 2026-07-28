package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertNull;

public class CORSFilterTest {
    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAllowsCORSHeaderOnV1AlphaSuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        assertNull(act.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testAllowsAllowMethodsHeaderOnV1AlphaGB() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/GB");
        assertNull(act.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testAllowsAllowHeadersOnV1AlphaList() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("codes", "US,CA").when().get("/v1/alpha");
        assertNull(act.getHeader("Access-Control-Allow-Headers"));
    }

    @Test(timeout = 60000)
    public void testCacheControlHeaderOnV1All() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/all");
        assertNull(act.getHeader("Cache-Control"));
    }

    @Test(timeout = 60000)
    public void testV1AlphaBadRequestReturns400() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/123");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1AlphaNotFoundReturns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/XYZ");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1CurrencySuccessHasCORSHeader() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/USD");
        assertNull(act.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testV1NameSuccessHasAllowMethodsHeader() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/France");
        assertNull(act.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testPostRootPreservesCORSHeader() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().body("{\"dummy\":\"value\"}").when().post("/");
        assertNull(act.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testV2AlphaFieldsHasCacheControl() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;population").when().get("/v2/alpha/US");
        assertNull(act.getHeader("Cache-Control"));
    }
}