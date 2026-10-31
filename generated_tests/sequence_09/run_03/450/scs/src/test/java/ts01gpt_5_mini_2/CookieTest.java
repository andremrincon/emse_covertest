package ts01gpt_5_mini_2;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import io.restassured.response.Response;
import java.net.URLEncoder;

public class CookieTest {

    private String baseUrl() {
        String b = System.getProperty("api.base", System.getenv("API_BASE_URL"));
        if (b == null || b.isEmpty()) {
            b = "http://localhost:8080";
        }
        return b;
    }

    @Test(timeout = 60000)
    public void test_userid_startsWithUser_returns1() throws Exception {
        String base = baseUrl();
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        String name = "userid";
        String val = "user12345";
        String site = "example.com";
        String n = URLEncoder.encode(name, "UTF-8");
        String v = URLEncoder.encode(val, "UTF-8");
        String s = URLEncoder.encode(site, "UTF-8");
        Response res = given().when().get(base + "/api/cookie/" + n + "/" + v + "/" + s);
        assertEquals("1", res.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_userid_length6_returns0() throws Exception {
        String base = baseUrl();
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        String name = "userid";
        String val = "usr123";
        String site = "localhost";
        String n = URLEncoder.encode(name, "UTF-8");
        String v = URLEncoder.encode(val, "UTF-8");
        String s = URLEncoder.encode(site, "UTF-8");
        Response res = given().when().get(base + "/api/cookie/" + n + "/" + v + "/" + s);
        assertEquals("0", res.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_userid_long_notStartsUser_returns0() throws Exception {
        String base = baseUrl();
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        String name = "userid";
        String val = "abcd12345";
        String site = "example.com";
        String n = URLEncoder.encode(name, "UTF-8");
        String v = URLEncoder.encode(val, "UTF-8");
        String s = URLEncoder.encode(site, "UTF-8");
        Response res = given().when().get(base + "/api/cookie/" + n + "/" + v + "/" + s);
        assertEquals("0", res.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_session_am_abccom_returns1() throws Exception {
        String base = baseUrl();
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        String name = "session";
        String val = "am";
        String site = "abc.com";
        String n = URLEncoder.encode(name, "UTF-8");
        String v = URLEncoder.encode(val, "UTF-8");
        String s = URLEncoder.encode(site, "UTF-8");
        Response res = given().when().get(base + "/api/cookie/" + n + "/" + v + "/" + s);
        assertEquals("1", res.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_session_am_other_returns2() throws Exception {
        String base = baseUrl();
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        String name = "session";
        String val = "am";
        String site = "other.com";
        String n = URLEncoder.encode(name, "UTF-8");
        String v = URLEncoder.encode(val, "UTF-8");
        String s = URLEncoder.encode(site, "UTF-8");
        Response res = given().when().get(base + "/api/cookie/" + n + "/" + v + "/" + s);
        assertEquals("2", res.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_othername_returns0() throws Exception {
        String base = baseUrl();
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        String name = "foo";
        String val = "anything";
        String site = "example.com";
        String n = URLEncoder.encode(name, "UTF-8");
        String v = URLEncoder.encode(val, "UTF-8");
        String s = URLEncoder.encode(site, "UTF-8");
        Response res = given().when().get(base + "/api/cookie/" + n + "/" + v + "/" + s);
        assertEquals("0", res.getBody().asString());
    }
}