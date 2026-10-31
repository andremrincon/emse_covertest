package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class RegexTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            String env = System.getenv("API_BASE");
            if (env != null && !env.isEmpty()) {
                base = env;
            } else {
                base = "http://localhost:8080";
            }
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPatEndpoint_urlMatches_returns200() throws Exception {
        String arrangeTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", URLEncoder.encode(arrangeTxt, "UTF-8")).then().statusCode(lessThan(300));
        String txt = "http://abc/def";
        Response resp = given().when().get("/api/pat/{txt}", URLEncoder.encode(txt, "UTF-8"));
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPatEndpoint_dateMatches_returns200() throws Exception {
        String arrangeTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", URLEncoder.encode(arrangeTxt, "UTF-8")).then().statusCode(lessThan(300));
        String txt = "wed12jan";
        Response resp = given().when().get("/api/pat/{txt}", URLEncoder.encode(txt, "UTF-8"));
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPatEndpoint_fpeMatches_returns200() throws Exception {
        String arrangeTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", URLEncoder.encode(arrangeTxt, "UTF-8")).then().statusCode(lessThan(300));
        String txt = "1.2e+34";
        Response resp = given().when().get("/api/pat/{txt}", URLEncoder.encode(txt, "UTF-8"));
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPatEndpoint_noneMatches_returns200() throws Exception {
        String arrangeTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", URLEncoder.encode(arrangeTxt, "UTF-8")).then().statusCode(lessThan(300));
        String txt = "this_does_not_match_any_pattern_123";
        Response resp = given().when().get("/api/pat/{txt}", URLEncoder.encode(txt, "UTF-8"));
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPatWithPattern_endpoint_returns200() throws Exception {
        String arrangeTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", URLEncoder.encode(arrangeTxt, "UTF-8")).then().statusCode(lessThan(300));
        String txt = "ABABCABAB";
        String pat = "ABAB";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", URLEncoder.encode(txt, "UTF-8"), URLEncoder.encode(pat, "UTF-8"));
        assertEquals(200, resp.getStatusCode());
    }
}