package ts01gpt_5_mini;

import org.junit.Test;
import org.junit.BeforeClass;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;
import java.util.UUID;
import java.net.URLEncoder;
import org.junit.Assert;
import io.restassured.RestAssured;

public class RegexTest {

    private static String BASE;

    @BeforeClass
    public static void init() {
        BASE = System.getProperty("baseUrl", System.getenv("BASE_URL"));
        if (BASE == null) {
            BASE = "http://localhost:8080";
        }
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void testRegexUrlBranchReturnsOk() throws Exception {
        given().when().get("/api/pat/" + URLEncoder.encode("health-"+UUID.randomUUID().toString(),"UTF-8")).then().statusCode(lessThan(300));
        String txt = "http://a/b";
        Response resp = given().when().get("/api/pat/" + URLEncoder.encode(txt, "UTF-8"));
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testRegexDateBranchReturnsOk() throws Exception {
        given().when().get("/api/pat/" + URLEncoder.encode("health2-"+UUID.randomUUID().toString(),"UTF-8")).then().statusCode(lessThan(300));
        String txt = "mon12jan";
        Response resp = given().when().get("/api/pat/" + URLEncoder.encode(txt, "UTF-8"));
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testRegexFpeBranchReturnsOk() throws Exception {
        given().when().get("/api/pat/" + URLEncoder.encode("health3-"+UUID.randomUUID().toString(),"UTF-8")).then().statusCode(lessThan(300));
        String txt = "1.2e+03";
        Response resp = given().when().get("/api/pat/" + URLEncoder.encode(txt, "UTF-8"));
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testRegexNoneBranchReturnsOk() throws Exception {
        given().when().get("/api/pat/" + URLEncoder.encode("health4-"+UUID.randomUUID().toString(),"UTF-8")).then().statusCode(lessThan(300));
        String txt = "no-match-input-XYZ";
        Response resp = given().when().get("/api/pat/" + URLEncoder.encode(txt, "UTF-8"));
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testPatWithPatternEndpointReturnsOk() throws Exception {
        given().when().get("/api/pat/" + URLEncoder.encode("ping-"+UUID.randomUUID().toString(),"UTF-8")).then().statusCode(lessThan(300));
        String txt = "ABABCABAB";
        String pat = "ABAB";
        Response resp = given().when().get("/api/pat/" + URLEncoder.encode(txt, "UTF-8") + "/" + URLEncoder.encode(pat, "UTF-8"));
        Assert.assertEquals(200, resp.getStatusCode());
    }
}