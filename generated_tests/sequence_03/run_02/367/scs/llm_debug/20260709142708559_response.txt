package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import org.junit.Assert;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CookieTest {
    private static final String BASE = Optional.ofNullable(System.getProperty("base.url"))
            .orElse(Optional.ofNullable(System.getenv("BASE_URL")).orElse("http://localhost:8080"));

    @Test(timeout = 60000)
    public void testUseridValid() {
        given().when().get(BASE + "/api/pat/{txt}", "health").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", "userid", "user1234", "example.com");
        Assert.assertEquals("1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testUseridTooShort() {
        given().when().get(BASE + "/api/pat/{txt}", "setup").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", "userid", "usr", "example.com");
        Assert.assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testUseridLongNoUserPrefix() {
        given().when().get(BASE + "/api/pat/{txt}", "ready").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", "userid", "abcd12345", "example.com");
        Assert.assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSessionMatching() {
        given().when().get(BASE + "/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        Assert.assertEquals("1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSessionNonMatching() {
        given().when().get(BASE + "/api/pat/{txt}", "alive").then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/cookie/{name}/{val}/{site}", "session", "pm", "example.com");
        Assert.assertEquals("2", resp.getBody().asString());
    }
}