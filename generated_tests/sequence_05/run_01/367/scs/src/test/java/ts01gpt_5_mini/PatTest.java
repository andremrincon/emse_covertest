package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import java.net.URLEncoder;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPat_PatLenTwo_returns0() throws Exception {
        given().when().get("/api/pat/{txt}", URLEncoder.encode("sample text for arrange", "UTF-8")).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", URLEncoder.encode("irrelevant text", "UTF-8"), URLEncoder.encode("ab", "UTF-8"));
        Assert.assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testPat_PatternOnly_returns1() throws Exception {
        given().when().get("/api/pat/{txt}", URLEncoder.encode("arrange text", "UTF-8")).then().statusCode(lessThan(300));
        String txt = "xxABCyy";
        String pat = "ABC";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", URLEncoder.encode(txt, "UTF-8"), URLEncoder.encode(pat, "UTF-8"));
        Assert.assertEquals("1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testPat_ReverseOnly_returns2() throws Exception {
        given().when().get("/api/pat/{txt}", URLEncoder.encode("arrange again", "UTF-8")).then().statusCode(lessThan(300));
        String txt = "xxCBAyy";
        String pat = "ABC";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", URLEncoder.encode(txt, "UTF-8"), URLEncoder.encode(pat, "UTF-8"));
        Assert.assertEquals("2", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testPat_BothNonAdjacent_returnsIndex() throws Exception {
        given().when().get("/api/pat/{txt}", URLEncoder.encode("setup", "UTF-8")).then().statusCode(lessThan(300));
        String txt = "ABCxxCBA";
        String pat = "ABC";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", URLEncoder.encode(txt, "UTF-8"), URLEncoder.encode(pat, "UTF-8"));
        Assert.assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testPat_PalindromeAdjacent_returnsIndex() throws Exception {
        given().when().get("/api/pat/{txt}", URLEncoder.encode("setup2", "UTF-8")).then().statusCode(lessThan(300));
        String txt = "ABCCBA";
        String pat = "ABC";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", URLEncoder.encode(txt, "UTF-8"), URLEncoder.encode(pat, "UTF-8"));
        Assert.assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testPat_SingleParamEndpoint_status200() throws Exception {
        given().when().get("/api/pat/{txt}", URLEncoder.encode("The quick brown fox jumps over the lazy dog.", "UTF-8")).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}", URLEncoder.encode("The quick brown fox jumps over the lazy dog.", "UTF-8"));
        Assert.assertEquals(200, resp.getStatusCode());
    }

}