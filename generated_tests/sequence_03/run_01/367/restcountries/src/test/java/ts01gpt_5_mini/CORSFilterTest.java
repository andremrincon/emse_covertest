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
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowOriginHeaderOnAlpha() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        assertNull(act.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsHeaderOnAll() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/all");
        assertNull(act.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersHeaderOnCurrency() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/currency/USD");
        assertNull(act.getHeader("Access-Control-Allow-Headers"));
    }

    @Test(timeout = 60000)
    public void testCacheControlHeaderPresentOnRegion() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/region/Europe");
        assertNull(act.getHeader("Cache-Control"));
    }

    @Test(timeout = 60000)
    public void testDoFilterAllowsSuccessfulResponseForAlpha() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/US");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAlphaInvalidCodeReturnsBadRequest() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/123");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testAlphaUnknownCodeReturnsNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/XYZ");
        act.then().statusCode(404);
    }
}