package ts01gpt_5_mini;

import org.junit.Test;
import org.junit.BeforeClass;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;
import java.util.UUID;
import static org.junit.Assert.assertEquals;

public class CookieTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url", "http://localhost:8080");
        }
        base = base.endsWith("/") ? base.substring(0, base.length() - 1) : base;
        baseURI = base;
    }

    @Test(timeout = 60000)
    public void testUseridMatchesPrefix() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-" + id).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "user12345", "example.com");
        assertEquals("1", resp.asString());
    }

    @Test(timeout = 60000)
    public void testUseridNoPrefixOrDifferent() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-" + id).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "abcdefg", "localhost");
        assertEquals("0", resp.asString());
    }

    @Test(timeout = 60000)
    public void testSessionExactMatch() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-" + id).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        assertEquals("1", resp.asString());
    }

    @Test(timeout = 60000)
    public void testSessionNonMatchingProducesTwo() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-" + id).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "pm", "other.com");
        assertEquals("2", resp.asString());
    }
}