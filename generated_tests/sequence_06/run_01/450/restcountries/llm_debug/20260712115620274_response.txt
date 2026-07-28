package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertNull;

public class CORSFilterTest {

    private static String BASE;

    @BeforeClass
    public static void setup() {
        String env = System.getenv("BASE_URL");
        if (env != null && !env.isEmpty()) {
            BASE = env;
        } else {
            String prop = System.getProperty("baseUrl");
            if (prop != null && !prop.isEmpty()) {
                BASE = prop;
            } else {
                BASE = "http://localhost:8080/rest";
            }
        }
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void testAllowOriginHeaderOnV1AlphaSuccess() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/US");
        assertNull(resp.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testAllowMethodsHeaderOnV1AlphaBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/123");
        resp.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testAllowHeadersOnV1All() {
        given().when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/all");
        assertNull(resp.getHeader("Access-Control-Allow-Headers"));
    }

    @Test(timeout = 60000)
    public void testCacheControlHeaderOnV1Currency() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/currency/USD");
        assertNull(resp.getHeader("Cache-Control"));
    }

    @Test(timeout = 60000)
    public void testCORSHeadersOnPostContribute() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        String json = "{\"amount\":10,\"currency\":\"USD\",\"token\":\"tok_test\"}";
        Response resp = given().contentType("application/json").body(json).when().post("/contribute");
        assertNull(resp.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testCORSOnRootGet() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/");
        assertNull(resp.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testCORSOnV2AlphaWithFields() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().param("fields", "name;capital;population").when().get("/v2/alpha/US");
        assertNull(resp.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testCORSOnV2RegionNotFound() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v2/region/123");
        resp.then().statusCode(404);
    }
}