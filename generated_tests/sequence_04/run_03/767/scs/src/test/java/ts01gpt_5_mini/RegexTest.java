package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;
import java.net.URLEncoder;
import java.util.UUID;
import static org.junit.Assert.assertEquals;

public class RegexTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE_URL");
        String prop = System.getProperty("api.base");
        RestAssured.baseURI = env != null ? env : (prop != null ? prop : "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testSubjectUrlBranch() throws Exception {
        String arrangeTxt = URLEncoder.encode("setup-" + UUID.randomUUID().toString(), "UTF-8");
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("http://abc/def", "UTF-8");
        Response resp = given().when().get("/api/pat/{txt}", txt).andReturn();
        assertEquals(200, resp.statusCode());
    }

    @Test(timeout = 60000)
    public void testSubjectDateBranch() throws Exception {
        String arrangeTxt = URLEncoder.encode("setup-" + UUID.randomUUID().toString(), "UTF-8");
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("mon12jan", "UTF-8");
        Response resp = given().when().get("/api/pat/{txt}", txt).andReturn();
        assertEquals(200, resp.statusCode());
    }

    @Test(timeout = 60000)
    public void testSubjectFpeBranch() throws Exception {
        String arrangeTxt = URLEncoder.encode("setup-" + UUID.randomUUID().toString(), "UTF-8");
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("12.34e+56", "UTF-8");
        Response resp = given().when().get("/api/pat/{txt}", txt).andReturn();
        assertEquals(200, resp.statusCode());
    }

    @Test(timeout = 60000)
    public void testSubjectNoneBranch() throws Exception {
        String arrangeTxt = URLEncoder.encode("setup-" + UUID.randomUUID().toString(), "UTF-8");
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("no-match-XYZ", "UTF-8");
        Response resp = given().when().get("/api/pat/{txt}", txt).andReturn();
        assertEquals(200, resp.statusCode());
    }

    @Test(timeout = 60000)
    public void testPatWithPatternEndpointInvokesBehavior() throws Exception {
        String arrangeTxt = URLEncoder.encode("setup-" + UUID.randomUUID().toString(), "UTF-8");
        given().when().get("/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("ABABCABAB", "UTF-8");
        String pat = URLEncoder.encode("ABAB", "UTF-8");
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat).andReturn();
        assertEquals(200, resp.statusCode());
    }
}