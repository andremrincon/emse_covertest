package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    private static String base;

    @BeforeClass
    public static void setup() {
        String prop = System.getProperty("base.url");
        String env = System.getenv("BASE_URL");
        base = prop != null && !prop.isEmpty() ? prop : (env != null && !env.isEmpty() ? env : "http://localhost:8080");
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test(timeout = 60000)
    public void testUrlPatternTriggersEndpointReturns200() throws Exception {
        String uuid = UUID.randomUUID().toString();
        given().when().get(base + "/api/text2txt/{w}/{x}/{y}", uuid, "quick", "brown").then().statusCode(lessThan(300));
        String txt = "http://abc/def";
        String encoded = URLEncoder.encode(txt, "UTF-8");
        Response act = given().when().get(base + "/api/pat/{txt}", encoded);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDatePatternTriggersEndpointReturns200() throws Exception {
        String uuid = UUID.randomUUID().toString();
        given().when().get(base + "/api/text2txt/{w}/{x}/{y}", uuid, "quick", "brown").then().statusCode(lessThan(300));
        String txt = "mon12jan";
        String encoded = URLEncoder.encode(txt, "UTF-8");
        Response act = given().when().get(base + "/api/pat/{txt}", encoded);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFpePatternTriggersEndpointReturns200() throws Exception {
        String uuid = UUID.randomUUID().toString();
        given().when().get(base + "/api/text2txt/{w}/{x}/{y}", uuid, "quick", "brown").then().statusCode(lessThan(300));
        String txt = "1.2e+34";
        String encoded = URLEncoder.encode(txt, "UTF-8");
        Response act = given().when().get(base + "/api/pat/{txt}", encoded);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonePatternTriggersEndpointReturns200() throws Exception {
        String uuid = UUID.randomUUID().toString();
        given().when().get(base + "/api/text2txt/{w}/{x}/{y}", uuid, "quick", "brown").then().statusCode(lessThan(300));
        String txt = "xyz";
        String encoded = URLEncoder.encode(txt, "UTF-8");
        Response act = given().when().get(base + "/api/pat/{txt}", encoded);
        act.then().statusCode(200);
    }
}