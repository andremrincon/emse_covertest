package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowOriginOnV1AlphaSuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/alpha/US");
        Assert.assertNull(r.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsOnV1AlphaBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/alpha/123");
        Assert.assertNull(r.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersOnV1AlphaNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/alpha/XYZ");
        Assert.assertNull(r.getHeader("Access-Control-Allow-Headers"));
    }

    @Test(timeout = 60000)
    public void testCacheControlHeaderOnV1All() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response r = given().when().get("/v1/all");
        Assert.assertNull(r.getHeader("Cache-Control"));
    }

    @Test(timeout = 60000)
    public void testHeadersPresentOnPostContribute() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        String payload = "{\"id\":\"" + UUID.randomUUID().toString() + "\",\"amount\":1}";
        Response r = given().contentType("application/json").body(payload).when().post("/contribute");
        Assert.assertNull(r.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testAccessControlOnV2AlphaWithFields() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response r = given().queryParam("fields", "name;capital;population").when().get("/v2/alpha/US");
        Assert.assertNull(r.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testCacheControlOnV1NameFullTextTrue() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response r = given().queryParam("fullText", "true").when().get("/v1/name/France");
        Assert.assertNull(r.getHeader("Cache-Control"));
    }

    @Test(timeout = 60000)
    public void testAllowMethodsHeaderOnV2PostMethodNotAllowed() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response r = given().contentType("application/json").body("{}").when().post("/v2");
        Assert.assertNull(r.getHeader("Access-Control-Allow-Methods"));
    }
}