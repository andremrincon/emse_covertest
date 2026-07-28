package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPatEndpoint_withUrlPattern_returns200() throws Exception {
        String arrangeTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", URLEncoder.encode(arrangeTxt, StandardCharsets.UTF_8.name())).then().statusCode(lessThan(300));
        String txt = "http://abc/def";
        String encoded = URLEncoder.encode(txt, StandardCharsets.UTF_8.name());
        Response act = given().when().get("/api/pat/{txt}", encoded);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatEndpoint_withDatePattern_returns200() throws Exception {
        String arrangeTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", URLEncoder.encode(arrangeTxt, StandardCharsets.UTF_8.name())).then().statusCode(lessThan(300));
        String txt = "mon12jan";
        String encoded = URLEncoder.encode(txt, StandardCharsets.UTF_8.name());
        Response act = given().when().get("/api/pat/{txt}", encoded);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatEndpoint_withFpePattern_returns200() throws Exception {
        String arrangeTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", URLEncoder.encode(arrangeTxt, StandardCharsets.UTF_8.name())).then().statusCode(lessThan(300));
        String txt = "1.2e+03";
        String encoded = URLEncoder.encode(txt, StandardCharsets.UTF_8.name());
        Response act = given().when().get("/api/pat/{txt}", encoded);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatEndpoint_withNonePattern_returns200() throws Exception {
        String arrangeTxt = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", URLEncoder.encode(arrangeTxt, StandardCharsets.UTF_8.name())).then().statusCode(lessThan(300));
        String txt = "foobar";
        String encoded = URLEncoder.encode(txt, StandardCharsets.UTF_8.name());
        Response act = given().when().get("/api/pat/{txt}", encoded);
        act.then().statusCode(200);
    }
}