package ts01gpt_5_mini;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;

public class RegexTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    private static String enc(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(timeout = 60000)
    public void testUrlPatternTriggersSubject_returns200() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}", enc("http://a/b"));
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDatePatternTriggersSubject_returns200() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}", "wed05mar");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFloatingPointExponentPatternTriggersSubject_returns200() {
        given().when().get("/api/pat/{txt}", "abc").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}", enc("15.5e+12"));
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoPatternMatchesSubject_returns200() {
        given().when().get("/api/pat/{txt}", "The quick brown fox").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}", "xyz");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatEndpointWithPattern_returns200() {
        given().when().get("/api/pat/{txt}", "sample").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "ABABCABAB", "ABAB");
        resp.then().statusCode(200);
    }
}