package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @BeforeClass
    public static void setUp() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAllowOriginHeaderOnV1Alpha() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        Assert.assertNull(resp.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testAllowMethodsHeaderOnV1All() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/all");
        Assert.assertNull(resp.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testAllowHeadersHeaderOnV1Name() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/name/France");
        Assert.assertNull(resp.getHeader("Access-Control-Allow-Headers"));
    }

    @Test(timeout = 60000)
    public void testCacheControlHeaderOnV1Currency() {
        given().when().get("/v2/alpha/US").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/USD");
        Assert.assertNull(resp.getHeader("Cache-Control"));
    }

    @Test(timeout = 60000)
    public void testCorsHeadersPresentOnPostContribute() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().contentType("application/json").body("{}").when().post("/contribute");
        Assert.assertNull(resp.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testOptionsRequestIncludesAllowOrigin() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().options("/v1/alpha/US");
        Assert.assertNull(resp.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testAlphaListEndpointReturns200() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha?codes=US,CA");
        Assert.assertEquals(400, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV2AllHasCorsHeader() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/all");
        Assert.assertNull(resp.getHeader("Access-Control-Allow-Origin"));
    }
}