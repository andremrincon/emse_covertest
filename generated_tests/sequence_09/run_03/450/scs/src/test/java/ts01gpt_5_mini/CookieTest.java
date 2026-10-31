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
    private static String base;

    @BeforeClass
    public static void setup() {
        String env = System.getenv("BASE_URL");
        String prop = System.getProperty("base.url");
        base = env != null && !env.isEmpty() ? env : (prop != null && !prop.isEmpty() ? prop : "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_userid_with_short_value_returns_0() {
        given().when().get(base + "/api/pat/a").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/cookie/userid/abc/localhost");
        assertEquals("0", act.asString());
    }

    @Test(timeout = 60000)
    public void test_userid_with_long_nonuser_prefix_returns_0() {
        given().when().get(base + "/api/pat/The%20quick%20brown%20fox").then().statusCode(lessThan(300));
        String unique = "abcdexy" + UUID.randomUUID().toString().substring(0, 4);
        Response act = given().when().get(base + "/api/cookie/userid/" + unique + "/example.com");
        assertEquals("0", act.asString());
    }

    @Test(timeout = 60000)
    public void test_userid_with_long_user_prefix_returns_1() {
        given().when().get(base + "/api/pat/banana").then().statusCode(lessThan(300));
        String val = "user12345" + UUID.randomUUID().toString().substring(0, 3);
        Response act = given().when().get(base + "/api/cookie/userid/" + val + "/example.com");
        assertEquals("1", act.asString());
    }

    @Test(timeout = 60000)
    public void test_session_am_with_abc_com_returns_1() {
        given().when().get(base + "/api/pat/ABABCABAB").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/cookie/session/am/abc.com");
        assertEquals("1", act.asString());
    }

    @Test(timeout = 60000)
    public void test_session_nonmatching_returns_2() {
        given().when().get(base + "/api/pat/The").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/cookie/session/am/localhost");
        assertEquals("2", act.asString());
    }
}