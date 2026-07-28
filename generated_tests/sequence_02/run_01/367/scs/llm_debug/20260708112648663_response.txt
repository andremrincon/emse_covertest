package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import java.net.URLEncoder;

public class RegexTest {

    private static String BASE;

    @BeforeClass
    public static void init() {
        String prop = System.getProperty("baseUrl");
        String env = System.getenv("BASE_URL");
        if (prop != null && !prop.isEmpty()) {
            BASE = prop;
        } else if (env != null && !env.isEmpty()) {
            BASE = env;
        } else {
            BASE = "http://localhost:8080";
        }
    }

    private static String enc(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(timeout = 60000)
    public void testSubjectMatchesUrlPattern_returns200() {
        given().when().get(BASE + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/pat/{txt}", enc("http://abc/def")).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectMatchesDatePattern_returns200() {
        given().when().get(BASE + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/pat/{txt}", enc("mon12jan")).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectMatchesFpePattern_returns200() {
        given().when().get(BASE + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/pat/{txt}", enc("12.3e+45")).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectMatchesNonePattern_returns200() {
        given().when().get(BASE + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/pat/{txt}", enc("foobar")).then().statusCode(200);
    }
}