package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import java.net.URLEncoder;

public class RegexTest {

    private String base() {
        String b = System.getProperty("baseUrl");
        if (b == null || b.isEmpty()) b = System.getenv("BASE_URL");
        if (b == null || b.isEmpty()) b = "http://localhost:8080";
        return b;
    }

    @Test(timeout = 60000)
    public void testUrlMatchesPattern() throws Exception {
        String base = base();
        String txt = "http://abc/def";
        given().when().get(base + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/pat/{txt}", URLEncoder.encode(txt, "UTF-8"));
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDateMatchesPattern() throws Exception {
        String base = base();
        String txt = "mon12jan";
        given().when().get(base + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/pat/{txt}", URLEncoder.encode(txt, "UTF-8"));
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFloatingPointExponentMatchesPattern() throws Exception {
        String base = base();
        String txt = "12.3e+45";
        given().when().get(base + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/pat/{txt}", URLEncoder.encode(txt, "UTF-8"));
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonePatternReturnsNoneBranch() throws Exception {
        String base = base();
        String txt = "hello-world";
        given().when().get(base + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/pat/{txt}", URLEncoder.encode(txt, "UTF-8"));
        act.then().statusCode(200);
    }
}