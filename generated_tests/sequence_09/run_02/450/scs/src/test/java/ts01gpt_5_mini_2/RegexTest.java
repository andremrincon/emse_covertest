package ts01gpt_5_mini_2;

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
    public static void init() {
        base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUrlPatternReturns200() throws Exception {
        String arrange = "arrange-" + UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/{txt}", arrange).then().statusCode(lessThan(300));
        String raw = "http://a/a";
        String encoded = URLEncoder.encode(raw, "UTF-8");
        Response act = given().when().get(base + "/api/pat/{txt}", encoded);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDatePatternReturns200() {
        String arrange = "arrange-" + UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/{txt}", arrange).then().statusCode(lessThan(300));
        String testTxt = "wed12jan";
        Response act = given().when().get(base + "/api/pat/{txt}", testTxt);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFpePatternReturns200() {
        String arrange = "arrange-" + UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/{txt}", arrange).then().statusCode(lessThan(300));
        String testTxt = "1.2e+03";
        Response act = given().when().get(base + "/api/pat/{txt}", testTxt);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNonePatternReturns200() {
        String arrange = "arrange-" + UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/{txt}", arrange).then().statusCode(lessThan(300));
        String testTxt = "xyz";
        Response act = given().when().get(base + "/api/pat/{txt}", testTxt);
        act.then().statusCode(200);
    }
}