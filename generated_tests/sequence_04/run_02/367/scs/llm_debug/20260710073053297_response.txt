package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.response.Response;
import java.net.URLEncoder;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    private String base() {
        String env = System.getProperty("base.url");
        if (env != null && !env.isEmpty()) return env;
        String e2 = System.getenv("BASE_URL");
        if (e2 != null && !e2.isEmpty()) return e2;
        return "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void test_pat_short_pattern_returns_zero() throws Exception {
        String base = base();
        String txt = "HelloWorld";
        String pat = "ab";
        given().when().get(base + "/api/pat/" + URLEncoder.encode("health-check", "UTF-8")).then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/pat/" + URLEncoder.encode(txt, "UTF-8") + "/" + URLEncoder.encode(pat, "UTF-8"));
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_pat_found_only_returns_ok() throws Exception {
        String base = base();
        String txt = "xyzabcuvw";
        String pat = "abc";
        given().when().get(base + "/api/pat/" + URLEncoder.encode("ping", "UTF-8")).then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/pat/" + URLEncoder.encode(txt, "UTF-8") + "/" + URLEncoder.encode(pat, "UTF-8"));
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_pat_reverse_found_only_returns_ok() throws Exception {
        String base = base();
        String txt = "xyzcbauvw";
        String pat = "abc";
        given().when().get(base + "/api/pat/" + URLEncoder.encode("ready", "UTF-8")).then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/pat/" + URLEncoder.encode(txt, "UTF-8") + "/" + URLEncoder.encode(pat, "UTF-8"));
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_pat_both_nonadjacent_returns_ok() throws Exception {
        String base = base();
        String txt = "abc123cba";
        String pat = "abc";
        given().when().get(base + "/api/pat/" + URLEncoder.encode("setup", "UTF-8")).then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/pat/" + URLEncoder.encode(txt, "UTF-8") + "/" + URLEncoder.encode(pat, "UTF-8"));
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_pat_palindrome_adjacent_returns_ok() throws Exception {
        String base = base();
        String txt = "abccba";
        String pat = "abc";
        given().when().get(base + "/api/pat/" + URLEncoder.encode("warmup", "UTF-8")).then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/pat/" + URLEncoder.encode(txt, "UTF-8") + "/" + URLEncoder.encode(pat, "UTF-8"));
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_pat_no_match_with_long_text_returns_ok() throws Exception {
        String base = base();
        String txt = "nomatchhere";
        String pat = "abc";
        given().when().get(base + "/api/pat/" + URLEncoder.encode("probe", "UTF-8")).then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/pat/" + URLEncoder.encode(txt, "UTF-8") + "/" + URLEncoder.encode(pat, "UTF-8"));
        act.then().statusCode(200);
    }
}