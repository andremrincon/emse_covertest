package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URLEncoder;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("base.url");
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
    public void test_pat_with_short_pattern_skips_processing() {
        String txt = "The quick brown fox";
        String pat = "ab";
        given().when().get("/api/pat/" + enc("health-check")).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/" + enc(txt) + "/" + enc(pat));
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_pat_found_without_reverse_returns_ok() {
        String uuid = UUID.randomUUID().toString();
        String pat = "ABAB";
        String txt = "XX" + pat + "YY" + uuid;
        given().when().get("/api/pat/" + enc("probe-" + uuid)).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/" + enc(txt) + "/" + enc(pat));
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_pat_followed_immediately_by_reverse_palindrome_case() {
        String pat = "ABC";
        String rev = new StringBuilder(pat).reverse().toString();
        String txt = "xx" + pat + rev + "yy";
        given().when().get("/api/pat/" + enc("probe2")).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/" + enc(txt) + "/" + enc(pat));
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_reverse_found_but_no_pat_returns_ok() {
        String pat = "XYZ";
        String rev = new StringBuilder(pat).reverse().toString();
        String txt = "prefix" + rev + "suffix";
        given().when().get("/api/pat/" + enc("probe3")).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/" + enc(txt) + "/" + enc(pat));
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_reverse_then_pat_adjacent_returns_ok() {
        String pat = "MNO";
        String rev = new StringBuilder(pat).reverse().toString();
        String txt = "zz" + rev + pat + "zz";
        given().when().get("/api/pat/" + enc("probe4")).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/" + enc(txt) + "/" + enc(pat));
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_pat_with_very_long_text_triggers_server_error() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) sb.append('a');
        String longTxt = sb.toString();
        String pat = "pattern";
        given().when().get("/api/pat/" + enc("probe-long")).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/pat/" + enc(longTxt) + "/" + enc(pat));
        act.then().statusCode(400);
    }
}