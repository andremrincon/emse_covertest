package ts01gpt_5_mini_2;

import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class PatTest {

    private static String BASE;

    @BeforeClass
    public static void setup() {
        String cfg = System.getProperty("api.base");
        if (cfg == null || cfg.isEmpty()) {
            cfg = System.getenv("API_BASE");
        }
        if (cfg == null || cfg.isEmpty()) {
            cfg = "http://localhost:8080";
        }
        BASE = cfg;
    }

    private static String enc(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8").replace("+", "%20");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(timeout = 60000)
    public void test_pat_with_short_pattern_returns_zero() {
        String txt = "The quick brown fox";
        String pat = "aa";
        given().when().get(BASE + "/api/pat/" + enc("The quick brown fox")).then().statusCode(lessThan(300));
        Response r = given().when().get(BASE + "/api/pat/" + enc(txt) + "/" + enc(pat));
        String body = r.asString();
        assertEquals("0", body);
    }

    @Test(timeout = 60000)
    public void test_pat_found_without_reverse_returns_one() {
        String txt = "xxABCyy";
        String pat = "ABC";
        given().when().get(BASE + "/api/pat/" + enc("The quick brown fox")).then().statusCode(lessThan(300));
        Response r = given().when().get(BASE + "/api/pat/" + enc(txt) + "/" + enc(pat));
        String body = r.asString();
        assertEquals("1", body);
    }

    @Test(timeout = 60000)
    public void test_reverse_only_returns_two() {
        String txt = "xxCBAyy";
        String pat = "ABC";
        given().when().get(BASE + "/api/pat/" + enc("The quick brown fox")).then().statusCode(lessThan(300));
        Response r = given().when().get(BASE + "/api/pat/" + enc(txt) + "/" + enc(pat));
        String body = r.asString();
        assertEquals("2", body);
    }

    @Test(timeout = 60000)
    public void test_palindrome_pat_followed_by_reverse_returns_index_zero() {
        String txt = "ABCCBA";
        String pat = "ABC";
        given().when().get(BASE + "/api/pat/" + enc("The quick brown fox")).then().statusCode(lessThan(300));
        Response r = given().when().get(BASE + "/api/pat/" + enc(txt) + "/" + enc(pat));
        String body = r.asString();
        assertEquals("0", body);
    }

    @Test(timeout = 60000)
    public void test_palindrome_reverse_followed_by_pat_returns_index_zero() {
        String txt = "CBAABC";
        String pat = "ABC";
        given().when().get(BASE + "/api/pat/" + enc("The quick brown fox")).then().statusCode(lessThan(300));
        Response r = given().when().get(BASE + "/api/pat/" + enc(txt) + "/" + enc(pat));
        String body = r.asString();
        assertEquals("0", body);
    }

    @Test(timeout = 60000)
    public void test_pat_and_reverse_non_adjacent_returns_index_of_first_occurrence() {
        String txt = "ABCxxxCBA";
        String pat = "ABC";
        given().when().get(BASE + "/api/pat/" + enc("The quick brown fox")).then().statusCode(lessThan(300));
        Response r = given().when().get(BASE + "/api/pat/" + enc(txt) + "/" + enc(pat));
        String body = r.asString();
        assertEquals("0", body);
    }
}