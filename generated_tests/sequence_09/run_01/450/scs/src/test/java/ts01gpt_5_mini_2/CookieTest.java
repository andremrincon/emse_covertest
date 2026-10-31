package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CookieTest {

    private static String base;

    @BeforeClass
    public static void setup() {
        String cfg = System.getProperty("api.base");
        if (cfg == null || cfg.isEmpty()) cfg = System.getenv("API_BASE");
        if (cfg == null || cfg.isEmpty()) cfg = "http://localhost:8080";
        base = cfg;
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUseridLongStartsWithUser_returns1() {
        given().when().get(base + "/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/cookie/{name}/{val}/{site}", "UserId", "Userabcd1", "example.com");
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridLongNotStartingUser_returns0() {
        given().when().get(base + "/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/cookie/{name}/{val}/{site}", "userid", "notuserlongval", "localhost");
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testUseridShortValue_returns0() {
        given().when().get(base + "/api/text2txt/{word1}/{word2}/{word3}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/cookie/{name}/{val}/{site}", "userid", "user", "example.com");
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionMatchAmAndAbcCom_returns1() {
        given().when().get(base + "/api/calc/{op}/{arg1}/{arg2}", "add", "15.5", "4.5").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/cookie/{name}/{val}/{site}", "SESSION", "AM", "ABC.COM");
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionNonMatching_returns2() {
        given().when().get(base + "/api/ordered4/{w}/{x}/{z}/{y}", "zebra", "yak", "x-ray", "wolf").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/cookie/{name}/{val}/{site}", "session", "pm", "example.com");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testOtherNameReturns0() {
        given().when().get(base + "/api/pat/{txt}", "banana").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/cookie/{name}/{val}/{site}", "visitor", "anyval", "example.com");
        resp.then().body(equalTo("0"));
    }
}