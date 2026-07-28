package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CookieTest {

    private final String base = System.getProperty("api.base", System.getenv("API_BASE_URL")) == null ? "http://localhost:8080" : (System.getProperty("api.base", System.getenv("API_BASE_URL")));

    @Test(timeout = 60000)
    public void testUseridWithUserPrefixReturns1() {
        given().when().get(base + "/api/pat/arrange").then().statusCode(lessThan(300));
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String val = "user" + uuid;
        Response resp = given().when().get(base + "/api/cookie/{name}/{val}/{site}", "userid", val, "example.com");
        assertEquals("1", resp.asString());
    }

    @Test(timeout = 60000)
    public void testUseridLongButNotUserPrefixReturns0() {
        given().when().get(base + "/api/pat/setup").then().statusCode(lessThan(300));
        String val = "abcd12345";
        Response resp = given().when().get(base + "/api/cookie/{name}/{val}/{site}", "userid", val, "localhost");
        assertEquals("0", resp.asString());
    }

    @Test(timeout = 60000)
    public void testUseridShortValueReturns0() {
        given().when().get(base + "/api/pat/ready").then().statusCode(lessThan(300));
        String val = "usr1";
        Response resp = given().when().get(base + "/api/cookie/{name}/{val}/{site}", "userid", val, "example.com");
        assertEquals("0", resp.asString());
    }

    @Test(timeout = 60000)
    public void testSessionAmAndAbcComReturns1() {
        given().when().get(base + "/api/pat/seed").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        assertEquals("1", resp.asString());
    }

    @Test(timeout = 60000)
    public void testSessionNonMatchingReturns2() {
        given().when().get(base + "/api/pat/init").then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/cookie/{name}/{val}/{site}", "session", "pm", "other.com");
        assertEquals("2", resp.asString());
    }
}