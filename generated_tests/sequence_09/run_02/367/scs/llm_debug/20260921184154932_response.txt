package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("API_BASE");
        if (env == null || env.isEmpty()) {
            env = System.getenv("API_BASE");
        }
        if (env == null || env.isEmpty()) {
            env = "http://localhost:8080";
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void test_pat_short_pat_len_two_returns200() {
        given().when().get("/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "sampletext", "ab");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_pat_pat_found_no_reverse_returns200() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "add", "15.5", "4.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "xxABCyy", "ABC");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_pat_reverse_found_only_returns200() {
        given().when().get("/api/pat/{txt}", "Thequickbrownfox").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "xxCBAzz", "ABC");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_pat_palindrome_adjacent_pat_then_rev_returns200() {
        given().when().get("/api/text2txt/{word1}/{word2}/{word3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "ABCCBA", "ABC");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_pat_palindrome_adjacent_rev_then_pat_returns200() {
        given().when().get("/api/pat/{txt}", "healthcheck2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "CBAABC", "ABC");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_pat_both_nonadjacent_returns200() {
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "zebra", "yak", "x-ray", "wolf").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}/{pat}", "ABCxxxCBA", "ABC");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_pat_txt_endpoint_returns200() {
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-id", "abc-123-xyz-789", "example.com").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/pat/{txt}", "Thequickbrownfox");
        resp.then().statusCode(200);
    }
}