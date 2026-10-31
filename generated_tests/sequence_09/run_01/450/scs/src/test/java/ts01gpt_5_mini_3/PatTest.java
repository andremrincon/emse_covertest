package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class PatTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            String env = System.getenv("API_BASE");
            base = (env == null || env.isEmpty()) ? "http://localhost:8080" : env;
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_pat_txt_endpoint_status_ok() {
        given().when().get("/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}", "TheQuickBrownFox").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_pat_with_short_pattern_returns_zero() {
        given().when().get("/api/pat/{txt}", "setup-short").then().statusCode(lessThan(300));
        String txt = "irrelevanttext";
        String pat = "ab";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        assertEquals("0", resp.asString());
    }

    @Test(timeout = 60000)
    public void test_pat_when_pattern_found_returns_one() {
        given().when().get("/api/pat/{txt}", "setup-pattern").then().statusCode(lessThan(300));
        String pat = "abc";
        String txt = "xxabcyy";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        assertEquals("1", resp.asString());
    }

    @Test(timeout = 60000)
    public void test_pat_when_reverse_found_only_returns_two() {
        given().when().get("/api/pat/{txt}", "setup-reverse").then().statusCode(lessThan(300));
        String pat = "abc";
        String txt = "zzcbaqq";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        assertEquals("2", resp.asString());
    }

    @Test(timeout = 60000)
    public void test_pat_and_reverse_adjacent_after_pattern_returns_index() {
        given().when().get("/api/pat/{txt}", "setup-adjacent").then().statusCode(lessThan(300));
        String pat = "abc";
        String txt = "xxabccbazz";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        assertEquals("2", resp.asString());
    }

    @Test(timeout = 60000)
    public void test_reverse_then_pattern_adjacent_returns_index() {
        given().when().get("/api/pat/{txt}", "setup-revthenpat").then().statusCode(lessThan(300));
        String pat = "abc";
        String txt = "xxcbaabczz";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        assertEquals("2", resp.asString());
    }
}