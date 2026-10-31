package ts01gpt_5_mini_2;

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
        String env = System.getProperty("api.base");
        if (env == null || env.isEmpty()) {
            String e2 = System.getenv("API_BASE");
            if (e2 != null && !e2.isEmpty()) {
                env = e2;
            } else {
                env = "http://localhost:8080";
            }
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void test_userid_with_user_prefix_and_length_gt6_returns_1() {
        String unique = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "user1234-" + unique, "example.com");
        String body = resp.asString();
        assertEquals("1", body);
    }

    @Test(timeout = 60000)
    public void test_userid_length_gt6_but_not_starting_user_returns_0() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "abcd12345", "example.com");
        String body = resp.asString();
        assertEquals("0", body);
    }

    @Test(timeout = 60000)
    public void test_userid_length_leq6_returns_0() {
        given().when().get("/api/pat/{txt}", "ABABCABAB").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "userid", "usr", "example.com");
        String body = resp.asString();
        assertEquals("0", body);
    }

    @Test(timeout = 60000)
    public void test_session_am_and_abc_com_returns_1() {
        given().when().get("/api/pat/{txt}", "The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "am", "abc.com");
        String body = resp.asString();
        assertEquals("1", body);
    }

    @Test(timeout = 60000)
    public void test_session_non_matching_returns_2() {
        String unique = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "banana").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/cookie/{name}/{val}/{site}", "session", "xx-" + unique, "example.com");
        String body = resp.asString();
        assertEquals("2", body);
    }
}