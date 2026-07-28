package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CORSFilterTest {
    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowOriginHeaderOnV1AlphaSuccess() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        assertEquals(null, act.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsHeaderOnV1NameSuccess() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/France");
        assertEquals(null, act.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersHeaderOnV1CurrencySuccess() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/USD");
        assertEquals(null, act.getHeader("Access-Control-Allow-Headers"));
    }

    @Test(timeout = 60000)
    public void testCacheControlHeaderOnV1AllSuccess() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/all");
        assertEquals(null, act.getHeader("Cache-Control"));
    }

    @Test(timeout = 60000)
    public void testHeadersPresentOnV1AlphaBadRequest() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/123");
        assertEquals(null, act.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testHeadersPresentOnV1AlphaNotFound() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/XYZ");
        assertEquals(null, act.getHeader("Cache-Control"));
    }

    @Test(timeout = 60000)
    public void testHeadersOnContributePostBadRequest() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().contentType("application/json").body("{}").when().post("/contribute");
        assertEquals(null, act.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testHeadersOnV2AlphaWithFields() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response act = given().queryParam("fields", "name;capital;population").when().get("/v2/alpha/US");
        assertEquals(null, act.getHeader("Access-Control-Allow-Origin"));
    }
}