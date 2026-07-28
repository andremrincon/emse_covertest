package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    private static String base;

    @BeforeClass
    public static void init() {
        String env = System.getProperty("api.base");
        if (env == null || env.isEmpty()) {
            String env2 = System.getenv("API_BASE_URL");
            base = (env2 != null && !env2.isEmpty()) ? env2 : "http://localhost:8080";
        } else {
            base = env;
        }
    }

    @Test(timeout = 60000)
    public void testUrlPatternHandledByPatEndpoint() throws Exception {
        given().when().get(base + "/api/text2txt/{word1}/{word2}/{word3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        String txt = "http://abc/def";
        String enc = URLEncoder.encode(txt, "UTF-8");
        Response r = given().when().get(base + "/api/pat/{txt}", enc);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDatePatternHandledByPatEndpoint() {
        given().when().get(base + "/api/text2txt/{word1}/{word2}/{word3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        String txt = "mon12jan";
        Response r = given().when().get(base + "/api/pat/{txt}", txt);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFpePatternHandledByPatEndpoint() {
        given().when().get(base + "/api/text2txt/{word1}/{word2}/{word3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        String txt = "12.34e+05";
        Response r = given().when().get(base + "/api/pat/{txt}", txt);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoMatchHandledByPatEndpoint() {
        given().when().get(base + "/api/text2txt/{word1}/{word2}/{word3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        String txt = "banana";
        Response r = given().when().get(base + "/api/pat/{txt}", txt);
        r.then().statusCode(200);
    }
}