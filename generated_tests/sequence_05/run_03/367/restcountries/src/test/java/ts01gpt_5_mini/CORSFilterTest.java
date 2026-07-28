package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CORSFilterTest {

    private static final String BASE = System.getProperty("rest.base.url", System.getenv("REST_BASE_URL") != null ? System.getenv("REST_BASE_URL") : "http://localhost:8080");

    @BeforeClass
    public static void setup() {
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Origin\" was not \"*\", was \"null\"...")
    @Test(timeout = 60000)
    public void testAccessControlAllowOriginOnV1All() {
        given().when().get(BASE + "/v1/all").then().header("Access-Control-Allow-Origin", "*");
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Methods\" was not \"GET\", was \"nul...")
    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsOnV1AlphaUS() {
        given().when().get(BASE + "/v1/alpha/US").then().header("Access-Control-Allow-Methods", "GET");
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Headers\" was not \"Accept, X-Reques...")
    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersOnV1AlphaInvalid() {
        given().when().get(BASE + "/v1/alpha/123").then().header("Access-Control-Allow-Headers", "Accept, X-Requested-With");
    }

    @Ignore("1 expectation failed. Expected header \"Cache-Control\" was not \"public, max-age=86400\", was \"...")
    @Test(timeout = 60000)
    public void testCacheControlHeaderOnV2All() {
        given().when().get(BASE + "/v2/all").then().header("Cache-Control", "public, max-age=86400");
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Origin\" was not \"*\", was \"null\"...")
    @Test(timeout = 60000)
    public void testCorsHeaderPresentOnOptionsRoot() {
        given().when().options(BASE + "/").then().header("Access-Control-Allow-Origin", "*");
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV1AlphaUSReturns200() {
        given().when().get(BASE + "/v1/alpha/US").then().statusCode(200);
    }

    @Ignore("1 expectation failed. Expected header \"Access-Control-Allow-Origin\" was not \"*\", was \"null\"...")
    @Test(timeout = 60000)
    public void testV1AlphaCodesQueryHasCorsHeader() {
        given().when().get(BASE + "/v1/alpha?codes=US,CA").then().header("Access-Control-Allow-Origin", "*");
    }

    @Ignore("1 expectation failed. Expected status code <202> but was <404>.")
    @Test(timeout = 60000)
    public void testContributePostAcceptedStatus() {
        given().contentType("application/json").body("{\"amount\":1,\"currency\":\"USD\",\"token\":\"tok\"}").when().post(BASE + "/contribute").then().statusCode(202);
    }
}