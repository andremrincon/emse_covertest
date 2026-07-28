package ts01gpt_5_mini;

import org.junit.Test;
import org.junit.Assert;
import java.net.URLEncoder;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {
    private static final String BASE;
    static {
        String b = System.getProperty("API_BASE_URL");
        if (b == null || b.isEmpty()) b = System.getenv("API_BASE_URL");
        if (b == null || b.isEmpty()) b = "http://localhost:8080";
        BASE = b;
    }

    @Test(timeout = 60000)
    public void testPatTooShort_returnsZero() throws Exception {
        given().when().get(BASE + "/api/pat/" + URLEncoder.encode("server-health-check", "UTF-8")).then().statusCode(lessThan(300));
        String txt = "irrelevant";
        String pat = "ab";
        Response resp = given().when().get(BASE + "/api/pat/" + URLEncoder.encode(txt, "UTF-8") + "/" + URLEncoder.encode(pat, "UTF-8"));
        Assert.assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testPatFoundOnly_returnsOne() throws Exception {
        given().when().get(BASE + "/api/pat/" + URLEncoder.encode("ping", "UTF-8")).then().statusCode(lessThan(300));
        String txt = "xxabcyy";
        String pat = "abc";
        Response resp = given().when().get(BASE + "/api/pat/" + URLEncoder.encode(txt, "UTF-8") + "/" + URLEncoder.encode(pat, "UTF-8"));
        Assert.assertEquals("1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testReverseFoundOnly_returnsTwo() throws Exception {
        given().when().get(BASE + "/api/pat/" + URLEncoder.encode("ping2", "UTF-8")).then().statusCode(lessThan(300));
        String txt = "xxcbayy";
        String pat = "abc";
        Response resp = given().when().get(BASE + "/api/pat/" + URLEncoder.encode(txt, "UTF-8") + "/" + URLEncoder.encode(pat, "UTF-8"));
        Assert.assertEquals("2", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testPatAndReverseNonAdjacent_returnsIndex() throws Exception {
        given().when().get(BASE + "/api/pat/" + URLEncoder.encode("probe", "UTF-8")).then().statusCode(lessThan(300));
        String txt = "xxABCDyyDCBAzz";
        String pat = "ABCD";
        Response resp = given().when().get(BASE + "/api/pat/" + URLEncoder.encode(txt, "UTF-8") + "/" + URLEncoder.encode(pat, "UTF-8"));
        Assert.assertEquals("2", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testPatThenReverseAdjacent_returnsIndex() throws Exception {
        given().when().get(BASE + "/api/pat/" + URLEncoder.encode("alive", "UTF-8")).then().statusCode(lessThan(300));
        String txt = "xxABCDDCBAzz";
        String pat = "ABCD";
        Response resp = given().when().get(BASE + "/api/pat/" + URLEncoder.encode(txt, "UTF-8") + "/" + URLEncoder.encode(pat, "UTF-8"));
        Assert.assertEquals("2", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testReverseThenPatAdjacent_returnsIndex() throws Exception {
        given().when().get(BASE + "/api/pat/" + URLEncoder.encode("alive2", "UTF-8")).then().statusCode(lessThan(300));
        String txt = "xxDCBAABCDzz";
        String pat = "ABCD";
        Response resp = given().when().get(BASE + "/api/pat/" + URLEncoder.encode(txt, "UTF-8") + "/" + URLEncoder.encode(pat, "UTF-8"));
        Assert.assertEquals("2", resp.getBody().asString());
    }
}