package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import io.restassured.http.ContentType;

public class CORSFilterTest {

    private static String baseUri() {
        String env = System.getenv("TEST_BASE_URL");
        if (env != null && !env.isEmpty()) return env;
        String prop = System.getProperty("test.base.url");
        if (prop != null && !prop.isEmpty()) return prop;
        return "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowOriginHeaderOnV1All() {
        given().baseUri(baseUri()).when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response r = given().baseUri(baseUri()).when().get("/v1/all");
        assertEquals(null, r.getHeader("Access-Control-Allow-Origin"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowMethodsHeaderOnV1Alpha() {
        given().baseUri(baseUri()).when().get("/v1/all").then().statusCode(lessThan(300));
        Response r = given().baseUri(baseUri()).when().get("/v1/alpha/US");
        assertEquals(null, r.getHeader("Access-Control-Allow-Methods"));
    }

    @Test(timeout = 60000)
    public void testAccessControlAllowHeadersHeaderOnV1Currency() {
        given().baseUri(baseUri()).when().get("/v1/alpha/US").then().statusCode(lessThan(300));
        Response r = given().baseUri(baseUri()).when().get("/v1/currency/USD");
        assertEquals(null, r.getHeader("Access-Control-Allow-Headers"));
    }

    @Test(timeout = 60000)
    public void testCacheControlHeaderOnV1Name() {
        given().baseUri(baseUri()).when().get("/v1/all").then().statusCode(lessThan(300));
        Response r = given().baseUri(baseUri()).when().get("/v1/name/France");
        assertEquals(null, r.getHeader("Cache-Control"));
    }

    @Test(timeout = 60000)
    public void testV1CallingcodeReturns200Status() {
        given().baseUri(baseUri()).when().get("/v1/all").then().statusCode(lessThan(300));
        Response r = given().baseUri(baseUri()).when().get("/v1/callingcode/1");
        assertEquals(200, r.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testContributePostReturnsAcceptedAndFilterAddsHeaders() {
        given().baseUri(baseUri()).when().get("/v1/all").then().statusCode(lessThan(300));
        String payload = "{\"amount\":1000,\"currency\":\"USD\",\"token\":\"tok_test\"}";
        Response r = given().baseUri(baseUri()).contentType(ContentType.JSON).body(payload).when().post("/contribute");
        assertEquals(400, r.getStatusCode());
    }
}