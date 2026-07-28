package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

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
    public void testUseridValidStartsWithUserReturns1() {
        given().when().get("/api/pat/{txt}", "The quick brown fox").then().statusCode(lessThan(300));
        String unique = UUID.randomUUID().toString().replace("-", "");
        String val = ("user" + unique).substring(0, Math.max(7, ("user" + unique).length()));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", val, "example.com");
        assertEquals("1", resp.asString());
    }

    @Test(timeout = 60000)
    public void testUseridShortValueReturns0() {
        given().when().get("/api/pat/{txt}", "The quick brown fox").then().statusCode(lessThan(300));
        String val = "usr1";
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", val, "localhost");
        assertEquals("0", resp.asString());
    }

    @Test(timeout = 60000)
    public void testUseridLongButNotStartingUserReturns0() {
        given().when().get("/api/pat/{txt}", "The quick brown fox").then().statusCode(lessThan(300));
        String val = "abcd1234567";
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", val, "example.com");
        assertEquals("0", resp.asString());
    }

    @Test(timeout = 60000)
    public void testSessionExactMatchReturns1() {
        given().when().get("/api/pat/{txt}", "The quick brown fox").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        assertEquals("1", resp.asString());
    }

    @Test(timeout = 60000)
    public void testSessionNonMatchingReturns2() {
        given().when().get("/api/pat/{txt}", "The quick brown fox").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "pm", "other.com");
        assertEquals("2", resp.asString());
    }

    @Test(timeout = 60000)
    public void testOtherNameDefaultsTo0() {
        given().when().get("/api/pat/{txt}", "The quick brown fox").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "randomname", "someval", "somesite");
        assertEquals("0", resp.asString());
    }
}