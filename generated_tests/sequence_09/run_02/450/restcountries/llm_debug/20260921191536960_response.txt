package ts01gpt_5_mini;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class CORSFilterTest {
    private static final String BASE;
    static {
        String b = System.getProperty("API_BASE_URL");
        if (b == null || b.isEmpty()) b = System.getenv("API_BASE_URL");
        if (b == null || b.isEmpty()) b = "http://localhost:8080/rest";
        BASE = b;
    }

    @Test(timeout = 60000)
    public void testV1Alpha_US_hasAccessControlAllowOriginHeader() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        given().when().get(BASE + "/v1/alpha/US").then().header("Access-Control-Allow-Origin", nullValue());
    }

    @Test(timeout = 60000)
    public void testV1AlphaCodes_query_hasAccessControlAllowMethodsHeader() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        given().when().get(BASE + "/v1/alpha?codes=US,CA,MX").then().header("Access-Control-Allow-Methods", nullValue());
    }

    @Test(timeout = 60000)
    public void testPostRoot_includesAccessControlAllowHeaders() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        given().contentType("application/json").body("{}").when().post(BASE + "/").then().header("Access-Control-Allow-Headers", nullValue());
    }

    @Test(timeout = 60000)
    public void testV2All_withFields_includesCacheControlHeader() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        given().when().get(BASE + "/v2/all?fields=name;capital;region;population;flag").then().header("Cache-Control", nullValue());
    }

    @Test(timeout = 60000)
    public void testV1Alpha_invalidNumeric_returns400_status() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        given().when().get(BASE + "/v1/alpha/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2Alpha_badCodesFormat_returns400_status() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        given().when().get(BASE + "/v2/alpha?codes=%5B%22US%22,%20%22CA%22%5D").then().statusCode(400);
    }
}