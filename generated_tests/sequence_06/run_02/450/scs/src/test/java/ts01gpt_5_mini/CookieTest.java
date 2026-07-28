package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CookieTest {

    @BeforeClass
    public static void setup() {
        String cfg = System.getProperty("baseUrl");
        if (cfg == null || cfg.isEmpty()) {
            cfg = System.getenv("BASE_URL");
        }
        if (cfg == null || cfg.isEmpty()) {
            cfg = "http://localhost:8080";
        }
        RestAssured.baseURI = cfg;
    }

    @Test(timeout = 60000)
    public void testUseridReturnsOneWhenValueLongAndStartsWithUser() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "userabc", "example.com");
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridReturnsZeroWhenValueDoesNotStartWithUser() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "abcduser", "example.com");
        resp.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionReturnsOneWhenValAmAndSiteAbcCom() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        resp.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionReturnsTwoWhenNotMatchingAmAndAbcCom() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "pm", "abc.com");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testUnknownNameReturnsZero() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "other", "anything", "somewhere.com");
        resp.then().body(equalTo("0"));
    }
}