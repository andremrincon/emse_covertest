package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class PatTest {

    private static final String BASE;
    static {
        String cfg = System.getProperty("API_BASE");
        if (cfg == null || cfg.isEmpty()) {
            cfg = System.getenv("API_BASE");
        }
        if (cfg == null || cfg.isEmpty()) {
            cfg = System.getProperty("BASE_URL");
        }
        if (cfg == null || cfg.isEmpty()) {
            cfg = System.getenv("BASE_URL");
        }
        BASE = (cfg == null || cfg.isEmpty()) ? "http://localhost:8080" : cfg;
    }

    @Test(timeout = 60000)
    public void test_patlen_less_than_3_returns_zero() {
        String uid = UUID.randomUUID().toString();
        String txt = "abcdef" + uid;
        String pat = "ab";
        given().when().get(BASE + "/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/pat/{txt}/{pat}", txt, pat);
        assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_pat_only_found_returns_one() {
        String uid = UUID.randomUUID().toString();
        String txt = "xxxabcxxx" + uid;
        String pat = "abc";
        given().when().get(BASE + "/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/pat/{txt}/{pat}", txt, pat);
        assertEquals("1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_reverse_only_found_returns_two() {
        String uid = UUID.randomUUID().toString();
        String txt = "xxxcbazzz" + uid;
        String pat = "abc";
        given().when().get(BASE + "/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/pat/{txt}/{pat}", txt, pat);
        assertEquals("2", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_both_found_nonadjacent_returns_index_of_first_pat() {
        String uid = UUID.randomUUID().toString();
        String txt = "ZabcYYYcba" + uid;
        String pat = "abc";
        given().when().get(BASE + "/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/pat/{txt}/{pat}", txt, pat);
        assertEquals("1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_palindrome_pat_followed_by_reverse_adjacent_returns_index() {
        String uid = UUID.randomUUID().toString();
        String txt = "xxABAABAyy" + uid;
        String pat = "ABA";
        given().when().get(BASE + "/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/pat/{txt}/{pat}", txt, pat);
        assertEquals("2", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_reverse_first_then_pat_adjacent_returns_index_zero() {
        String uid = UUID.randomUUID().toString();
        String txt = "cbaabc" + uid;
        String pat = "abc";
        given().when().get(BASE + "/api/pat/{txt}", txt).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/pat/{txt}/{pat}", txt, pat);
        assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_single_param_pat_endpoint_returns_ok_status() {
        String uid = UUID.randomUUID().toString();
        String txt = "Thequickbrownfox" + uid;
        given().when().get(BASE + "/api/pat/{txt}/{pat}", txt, "nop").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/pat/{txt}", txt);
        assertEquals(200, resp.getStatusCode());
    }
}