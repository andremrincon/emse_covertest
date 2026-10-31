package ts01gpt_5_mini_2;

import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CookieTest {

    private String base() {
        String b = System.getProperty("baseUrl");
        if (b == null || b.isEmpty()) b = System.getenv("BASE_URL");
        if (b == null || b.isEmpty()) b = "http://localhost:8080";
        return b;
    }

    @Test(timeout = 60000)
    public void testUseridWithLeadingUserReturnsOne() {
        String base = base();
        given().when().get(base + "/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        String name = "userid";
        String val = "user" + UUID.randomUUID().toString().replace("-", "").substring(0,4);
        String site = "example.com";
        Response res = given().when().get(base + "/api/cookie/{name}/{val}/{site}", name, val, site);
        MatcherAssert.assertThat(res.getBody().asString(), equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testUseridLongButNotStartingUserReturnsZero() {
        String base = base();
        given().when().get(base + "/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        String name = "userid";
        String val = "abcd12345";
        String site = "localhost";
        Response res = given().when().get(base + "/api/cookie/{name}/{val}/{site}", name, val, site);
        MatcherAssert.assertThat(res.getBody().asString(), equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testUseridShortValueReturnsZero() {
        String base = base();
        given().when().get(base + "/api/pat/{txt}", UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        String name = "userid";
        String val = "usr1";
        String site = "example.com";
        Response res = given().when().get(base + "/api/cookie/{name}/{val}/{site}", name, val, site);
        MatcherAssert.assertThat(res.getBody().asString(), equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSessionAmAndAbcComReturnsOne() {
        String base = base();
        given().when().get(base + "/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        String name = "session";
        String val = "am";
        String site = "abc.com";
        Response res = given().when().get(base + "/api/cookie/{name}/{val}/{site}", name, val, site);
        MatcherAssert.assertThat(res.getBody().asString(), equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testSessionOtherReturnsTwo() {
        String base = base();
        given().when().get(base + "/api/pat/{txt}", "banana").then().statusCode(lessThan(300));
        String name = "session";
        String val = "notam";
        String site = "example.com";
        Response res = given().when().get(base + "/api/cookie/{name}/{val}/{site}", name, val, site);
        MatcherAssert.assertThat(res.getBody().asString(), equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testOtherNameReturnsZero() {
        String base = base();
        given().when().get(base + "/api/pat/{txt}", "The quick brown fox").then().statusCode(lessThan(300));
        String name = "something";
        String val = "whatever";
        String site = "example.com";
        Response res = given().when().get(base + "/api/cookie/{name}/{val}/{site}", name, val, site);
        MatcherAssert.assertThat(res.getBody().asString(), equalTo("0"));
    }
}