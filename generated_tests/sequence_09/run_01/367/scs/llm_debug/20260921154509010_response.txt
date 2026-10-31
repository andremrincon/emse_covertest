package ts01gpt_5_mini;

import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import io.restassured.response.Response;
import static org.junit.Assert.assertEquals;

public class CookieTest {

    private static final String BASE;
    static {
        String prop = System.getProperty("base.url");
        String env = System.getenv("BASE_URL");
        if (prop != null && !prop.isEmpty()) {
            BASE = prop;
        } else if (env != null && !env.isEmpty()) {
            BASE = env;
        } else {
            BASE = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testUseridStartsWithUserProducesOne() {
        given().when().get(BASE + "/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", "userid", "user1234", "example.com");
        assertEquals("1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testUseridShortProducesZero() {
        given().when().get(BASE + "/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", "userid", "us", "example.com");
        assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testUseridLongButNotUserProducesZero() {
        given().when().get(BASE + "/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", "userid", "abcd1234", "example.com");
        assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSessionAmAndAbcComProducesOne() {
        given().when().get(BASE + "/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        assertEquals("1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSessionOtherProducesTwo() {
        given().when().get(BASE + "/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", "session", "bm", "example.com");
        assertEquals("2", resp.getBody().asString());
    }
}