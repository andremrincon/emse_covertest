package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    private static String base;

    @BeforeClass
    public static void setup() {
        String env = System.getenv("BASE_URL");
        String prop = System.getProperty("base.url");
        base = env != null && !env.isEmpty() ? env : (prop != null && !prop.isEmpty() ? prop : "http://localhost:8080");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    private String enc(String s) {
        try {
            return URLEncoder.encode(s, StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(timeout = 60000)
    public void testUrlPatternTriggersUrlBranch() {
        String setupId = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/{txt}", setupId).then().statusCode(lessThan(300));
        String txt = "http://a/a";
        String encoded = enc(txt);
        Response resp = given().when().get(base + "/api/pat/{txt}", encoded);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDatePatternTriggersDateBranch() {
        String setupId = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/{txt}", setupId).then().statusCode(lessThan(300));
        String txt = "mon12jan";
        String encoded = enc(txt);
        Response resp = given().when().get(base + "/api/pat/{txt}", encoded);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFpePatternTriggersFpeBranch() {
        String setupId = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/{txt}", setupId).then().statusCode(lessThan(300));
        String txt = "12.3e+45";
        String encoded = enc(txt);
        Response resp = given().when().get(base + "/api/pat/{txt}", encoded);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonePatternTriggersNoneBranch() {
        String setupId = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/{txt}", setupId).then().statusCode(lessThan(300));
        String txt = "foobar";
        String encoded = enc(txt);
        Response resp = given().when().get(base + "/api/pat/{txt}", encoded);
        resp.then().statusCode(200);
    }
}