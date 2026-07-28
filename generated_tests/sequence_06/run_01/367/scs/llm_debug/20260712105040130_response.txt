package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {
    private static String BASE;

    @BeforeClass
    public static void init() {
        String p = System.getProperty("API_BASE");
        if (p == null || p.isEmpty()) {
            p = System.getenv("API_BASE");
        }
        if (p == null || p.isEmpty()) {
            p = "http://localhost:8080";
        }
        BASE = p;
    }

    @Test(timeout = 60000)
    public void testUseridValStartingWithUserReturnsOne() {
        given().when().get(BASE + "/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", "userid", "userXYZabc", "example.com");
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionAmAndAbcComReturnsOne() {
        given().when().get(BASE + "/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionNonAmReturnsTwo() {
        given().when().get(BASE + "/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", "session", "pm", "abc.com");
        resp.then().body(equalTo("2"));
    }
}