package ts01gpt_5_mini_2;

import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_pat_patlen_le2_returns_ok() {
        String txt = "abc-" + UUID.randomUUID().toString();
        String pat = "a";
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_pat_found_without_reverse_returns_ok() {
        String txt = "ZZZABABZZZ-" + UUID.randomUUID().toString();
        String pat = "ABAB";
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_pat_and_reverse_adjacent_palindrome_returns_ok() {
        String pat = "ABA";
        String txt = "XXX" + pat + new StringBuilder(pat).reverse().toString() + "YYY-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_reverse_then_pat_adjacent_returns_ok() {
        String pat = "ABC";
        String patrev = new StringBuilder(pat).reverse().toString();
        String txt = "PRE-" + patrev + pat + "-POST-" + UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "ABABCABAB").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_no_pat_or_reverse_returns_ok() {
        String txt = "no_match_here_" + UUID.randomUUID().toString();
        String pat = "XYZ";
        given().when().get("/api/pat/{txt}", "ABABCABAB").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        r.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_pat_txt_only_endpoint_returns_ok() {
        String txt = "The quick brown fox jumps over the lazy dog.";
        given().when().get("/api/pat/{txt}/{pat}", "sampletext", "a").then().statusCode(lessThan(300));
        Response r = given().when().get("/api/pat/{txt}", txt);
        r.then().statusCode(200);
    }
}