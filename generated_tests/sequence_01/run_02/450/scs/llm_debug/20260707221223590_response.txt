package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class RegexTest {

    private static String BASE;

    @BeforeClass
    public static void init() {
        String prop = System.getProperty("api.base");
        String env = System.getenv("API_BASE");
        if (prop != null && !prop.isEmpty()) {
            BASE = prop;
        } else if (env != null && !env.isEmpty()) {
            BASE = env;
        } else {
            BASE = "http://localhost:8080";
        }
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void testUrlInputTriggersUrlBranch() throws Exception {
        given().when().get(BASE + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("http://abc/def", StandardCharsets.UTF_8.name());
        Response act = given().when().get(BASE + "/api/pat/{txt}", txt);
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDateInputTriggersDateBranch() throws Exception {
        given().when().get(BASE + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("mon12jan", StandardCharsets.UTF_8.name());
        Response act = given().when().get(BASE + "/api/pat/{txt}", txt);
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFpeInputTriggersFpeBranch() throws Exception {
        given().when().get(BASE + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("1.2e+34", StandardCharsets.UTF_8.name());
        Response act = given().when().get(BASE + "/api/pat/{txt}", txt);
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testNoneInputTriggersNoneBranch() throws Exception {
        given().when().get(BASE + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        String txt = URLEncoder.encode("unmatched_input_123", StandardCharsets.UTF_8.name());
        Response act = given().when().get(BASE + "/api/pat/{txt}", txt);
        assertEquals(200, act.getStatusCode());
    }
}