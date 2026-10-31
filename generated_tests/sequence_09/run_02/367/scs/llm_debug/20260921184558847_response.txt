package ts01gpt_5_mini_3;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("base.url", System.getenv("BASE_URL"));
    }

    @Test(timeout = 60000)
    public void test_pat_then_reverse_adjacent_returns200() {
        given().when().get("/api/pat/{txt}", "The_quick_brown_fox").then().statusCode(lessThan(300));
        String txt = "ABABBABA";
        String pat = "ABAB";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_reverse_then_pat_adjacent_returns200() {
        given().when().get("/api/pat/{txt}", "Sample").then().statusCode(lessThan(300));
        String txt = "BABAABAB";
        String pat = "ABAB";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_pat_then_reverse_nonadjacent_returns200() {
        given().when().get("/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        String txt = "XXABABYYBABA";
        String pat = "ABAB";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_reverse_then_pat_nonadjacent_returns200() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        String txt = "BABAZZZABAB";
        String pat = "ABAB";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_short_pat_length_no_search_returns200() {
        given().when().get("/api/pat/{txt}", "check").then().statusCode(lessThan(300));
        String txt = "banana";
        String pat = "a";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_no_pat_or_reverse_returns200() {
        given().when().get("/api/pat/{txt}", "ready").then().statusCode(lessThan(300));
        String txt = "CCCCCCCCCCCC";
        String pat = "ABAB";
        Response resp = given().when().get("/api/pat/{txt}/{pat}", txt, pat);
        resp.then().statusCode(200);
    }
}