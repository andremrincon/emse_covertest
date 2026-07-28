package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PatTest {

    @BeforeClass
    public static void init() {
        String env = System.getenv("API_BASE");
        if (env != null && !env.isEmpty()) {
            RestAssured.baseURI = env;
        } else {
            RestAssured.baseURI = System.getProperty("api.base", "http://localhost:8080");
        }
    }

    @Test(timeout = 60000)
    public void test_PatOccurs_ReturnsOne() {
        given().when().get("/api/pat/{txt}", "arrange_pat_one").then().statusCode(lessThan(300));
        String txt = "zzabczz";
        String pat = "abc";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().assertThat().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void test_ReverseOccurs_ReturnsTwo() {
        given().when().get("/api/pat/{txt}", "arrange_reverse_two").then().statusCode(lessThan(300));
        String txt = "zzcbazz";
        String pat = "abc";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void test_PatAndReverseNonAdjacent_ReturnsIndexOfFirst() {
        given().when().get("/api/pat/{txt}", "arrange_both_nonadj").then().statusCode(lessThan(300));
        String txt = "abcxxxcba";
        String pat = "abc";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void test_PatThenReverseAdjacent_ReturnsIndexOfPalindromeStart() {
        given().when().get("/api/pat/{txt}", "arrange_pal_adj1").then().statusCode(lessThan(300));
        String txt = "abccba";
        String pat = "abc";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void test_ReverseThenPatAdjacent_ReturnsIndexOfPalindromeStart() {
        given().when().get("/api/pat/{txt}", "arrange_pal_adj2").then().statusCode(lessThan(300));
        String txt = "cbaabc";
        String pat = "abc";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().assertThat().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void test_ShortPattern_LengthLessThanThree_ReturnsZero() {
        given().when().get("/api/pat/{txt}", "arrange_short_pat").then().statusCode(lessThan(300));
        String txt = "xyzabx";
        String pat = "ab";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().assertThat().body(equalTo("0"));
    }
}