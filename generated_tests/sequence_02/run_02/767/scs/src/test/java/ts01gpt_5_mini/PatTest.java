package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class PatTest {

    private String baseUrl() {
        String url = System.getProperty("baseUrl");
        if (url != null && !url.isEmpty()) return url;
        url = System.getenv("BASE_URL");
        if (url != null && !url.isEmpty()) return url;
        return "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void test_patLengthTwo_no_search_path_returns200() {
        String base = baseUrl();
        String arrangeTxt = "arrange-" + UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = "someTextForTest";
        String pat = "ab";
        Response act = given().when().get(base + "/api/pat/{txt}/{pat}", txt, pat);
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_pat_present_in_text_returns200() {
        String base = baseUrl();
        String arrangeTxt = "setup-" + UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = "xxabcyy";
        String pat = "abc";
        Response act = given().when().get(base + "/api/pat/{txt}/{pat}", txt, pat);
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_reverse_present_only_returns200() {
        String base = baseUrl();
        String arrangeTxt = "seed-" + UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = "xxcbayy";
        String pat = "abc";
        Response act = given().when().get(base + "/api/pat/{txt}/{pat}", txt, pat);
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_both_pat_and_reverse_non_adjacent_returns200() {
        String base = baseUrl();
        String arrangeTxt = "prep-" + UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String txt = "xxabczzzcba";
        String pat = "abc";
        Response act = given().when().get(base + "/api/pat/{txt}/{pat}", txt, pat);
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_palindrome_pat_then_reverse_adjacent_returns200() {
        String base = baseUrl();
        String arrangeTxt = "prep2-" + UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String pat = "abc";
        String patrev = new StringBuilder(pat).reverse().toString();
        String txt = "xx" + pat + patrev + "yy";
        Response act = given().when().get(base + "/api/pat/{txt}/{pat}", txt, pat);
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void test_palindrome_reverse_then_pat_adjacent_returns200() {
        String base = baseUrl();
        String arrangeTxt = "prep3-" + UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/{txt}", arrangeTxt).then().statusCode(lessThan(300));
        String pat = "abc";
        String patrev = new StringBuilder(pat).reverse().toString();
        String txt = "zz" + patrev + pat + "qq";
        Response act = given().when().get(base + "/api/pat/{txt}/{pat}", txt, pat);
        assertEquals(200, act.getStatusCode());
    }
}