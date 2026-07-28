package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CORSFilterTest {
    private static final String BASE = Optional.ofNullable(System.getProperty("REST_BASE_URL"))
            .orElse(Optional.ofNullable(System.getenv("REST_BASE_URL")).orElse("http://localhost:8080/rest"));

    @Test(timeout = 60000)
    public void testAccessControlAllowOriginHeader_present_onV1Alpha() {
        given().when().get(BASE + "/v1/alpha/US").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v1/alpha/US").then().statusCode(200).extract().response();
        assertEquals(null, act.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsHeader_present_onV1All() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v1/all").then().statusCode(200).extract().response();
        assertEquals(null, act.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersHeader_present_onV1Name() {
        given().when().get(BASE + "/v1/name/France?fullText=false").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v1/name/France?fullText=false").then().statusCode(200).extract().response();
        assertEquals(null, act.getHeader("Access-Control-Allow-Headers"));
    }

    @Test(timeout = 60000)
    public void testCacheControlHeader_present_onV2All() {
        given().when().get(BASE + "/v2/all").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/v2/all").then().statusCode(200).extract().response();
        assertEquals(null, act.getHeader("Cache-Control"));
    }
}